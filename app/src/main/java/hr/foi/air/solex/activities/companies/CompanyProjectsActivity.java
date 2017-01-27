package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectHighlightsAdapter;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.companies.CompanyProjectsPresenter;
import hr.foi.air.solex.presenters.companies.CompanyProjectsPresenterImpl;

public class CompanyProjectsActivity extends DrawerActivity implements CompanyProjectsView {

    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;

    @BindView(R.id.activity_company_projects_recyclerView)
    RecyclerView recyclerView;

    CompanyProjectsPresenter mCompanyProjectsPresenter;
    private ProjectHighlightsAdapter projectHighlightsAdapter;
    ProjectHighlightsAdapter.ClickListener clickListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_projects;
    }

    private void setLayout(int id) {
        if (!User.isCurrentUser(UserType.COMPANY, id)) {
            btnAddNewProject.setVisibility(View.GONE);
            setTitle(getIntent().getExtras().getString("companyName") + R.string.projects_list);
        }
    }

    private int companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        companyId = getIntent().getExtras().getInt("companyId");

        this.mCompanyProjectsPresenter = new CompanyProjectsPresenterImpl(this);
        mCompanyProjectsPresenter.getProjects(companyId);
        setLayout(companyId);
    }

    @OnClick(R.id.btnAddNewProject)
    public void btnAddNewProjectClick() {
        Intent intent = new Intent(this, AddNewProjectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataArrived(List<ProfileScreenProject> projects) {;
        projectHighlightsAdapter = new ProjectHighlightsAdapter(projects, clickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ProjectHighlightsAdapter(projects, new ProjectHighlightsAdapter.ClickListener() {
            @Override
            public void onItemClick(ProfileScreenProject profileScreenProject) {
                openProjectActivity(profileScreenProject.getId(), User.isCurrentUser(UserType.COMPANY, companyId));
            }

            @Override
            public void onItemLongClick(ProfileScreenProject profileScreenProject) {
                if(profileScreenProject.getHighlightedStatus() == null){
                    mCompanyProjectsPresenter.addToHighlights(profileScreenProject.getId());
                }else{
                    mCompanyProjectsPresenter.updateHighlights(profileScreenProject.getId());
                }

            }
        }));
    }

    @Override
    public void onHighlightAddition() {
        Toast.makeText(getApplicationContext(), "The project has been added to highlights", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHighlightFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHighlightUpdate() {
        Toast.makeText(getApplicationContext(), "The project has been added to highlights", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHighlightUpdateFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void openProjectActivity(int projectId, boolean owner) {
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("isOwner", owner);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }
}
