package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.common.ProjectDisplayActivity;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectHighlightsAdapter;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.presenters.developers.DeveloperProjectsPresenter;
import hr.foi.air.solex.presenters.developers.DeveloperProjectsPresenterImpl;

public class DeveloperProjectsActivity extends DrawerActivity implements DeveloperProjectsView {
    @BindView(R.id.activity_developer_projects_recyclerView)
    RecyclerView recyclerView;

    DeveloperProjectsPresenter mDeveloperProjectsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mDeveloperProjectsPresenter = new DeveloperProjectsPresenterImpl(this);
        mDeveloperProjectsPresenter.getProjects(User.getInstance().getId());
    }

    @Override
    public void onDataArrived(List<ProfileScreenProject> projects) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ProjectHighlightsAdapter(projects, new ProjectHighlightsAdapter.ClickListener() {
            @Override
            public void onItemClick(ProfileScreenProject profileScreenProject) {
                openProjectActivity(profileScreenProject.getId());
            }

            @Override
            public void onItemLongClick(ProfileScreenProject profileScreenProject) {
                if (profileScreenProject.getHighlightedStatus() == 0) {
                    profileScreenProject.setHighlightedStatus(1);
                    mDeveloperProjectsPresenter.addToHighlights(profileScreenProject.getId());
                } else {
                    profileScreenProject.setHighlightedStatus(0);
                    mDeveloperProjectsPresenter.removeHighlights(profileScreenProject.getId());
                }

            }
        }));
    }

    @Override
    public void onHighlightAddition() {
        Toast.makeText(getApplicationContext(), R.string.added_to_highlights, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHighlightFailure(String message) {
        Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHighlightRemove() {
        Toast.makeText(getApplicationContext(), R.string.removed_on_highlights, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHighlightRemoveFailure(String message) {
        Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_SHORT).show();
    }

    private void openProjectActivity(int projectId) {
        Intent intent = new Intent(this, ProjectDisplayActivity.class);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_projects;
    }

}
