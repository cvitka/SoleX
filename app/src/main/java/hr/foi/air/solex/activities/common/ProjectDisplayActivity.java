package hr.foi.air.solex.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.companies.CompanyNeededCollaborationActivity;
import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationActivity;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.SelectedProjectInteractorImpl;
import hr.foi.air.solex.presenters.common.ProjectDisplayPresenter;
import hr.foi.air.solex.presenters.common.ProjectDisplayPresenterImpl;
import hr.foi.air.solex.utils.UserType;

public class ProjectDisplayActivity extends DrawerActivity implements ProjectDisplayView {

    @BindView(R.id.activity_project_display_lvNeededCollaborations)
    ListView lvNeededCollaborations;

    @BindView(R.id.activity_project_display_tvProjectName)
    TextView txtProjectName;

    @BindView(R.id.activity_project_display_tvCompanyName)
    TextView txtCompanyName;

    @BindView(R.id.activity_project_display_tvDescriptionData)
    TextView txtProjectDescription;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    Project mThisProject;
    ProjectDisplayPresenter mPresenter;
    List<ApiNeededCollaborations> neededCollaborations;
    private int projectId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_display;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        /** prosljedivanje presenteru */
        mPresenter = new ProjectDisplayPresenterImpl(this, new SelectedProjectInteractorImpl());
        //collabPresenter = new GetNeededCollaborationsPresenterImpl(this, new ApiNeededCollaborationsInteractorImpl());

        // String projectName;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
            } else {
                projectId = extras.getInt("projectId");
            }
        } else {
            projectId= savedInstanceState.getInt("projectId");
        }
        mPresenter.getProject(projectId);
        mPresenter.getNeededCollaboration(projectId);

        lvNeededCollaborations.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /** Disallow the touch request for parent scroll on touch of child view */
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @OnItemClick(R.id.activity_project_display_lvNeededCollaborations)
    public void lvNeededCollaborationsClick(AdapterView<?> adapter, View item, int pos, long id) {
        /** prosljedivanje na activitye ovisno o ulozi */

        ApiNeededCollaborations collab = neededCollaborations.get(pos);
        if (User.getInstance().getUserType() == UserType.DEVELOPER) {
            if (collab.getHasCollaborator() > 0) {
                Intent intent = new Intent(this, CollaborationActivity.class);
                intent.putExtra("collaborationName", collab.getDevNcollabNme());
                intent.putExtra("collaborationId", collab.getCollabId());
                intent.putExtra("isOwner", false);
                finish();
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, DeveloperNeededCollaborationActivity.class);
                intent.putExtra("applicantsNumber", collab.getApplicantNum());
                intent.putExtra("collaborationId", collab.getCollabId());
                intent.putExtra("companyName", mThisProject.getCompanyName());
                startActivity(intent);
            }
        } else {
            if (collab.getHasCollaborator() > 0) {
                Intent intent = new Intent(this, CollaborationActivity.class);
                intent.putExtra("collaborationName", collab.getDevNcollabNme());
                intent.putExtra("collaborationId", collab.getCollabId());
                intent.putExtra("isOwner", User.isCurrentUser(UserType.COMPANY, mThisProject.getCompanyId()));
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, CompanyNeededCollaborationActivity.class);
                intent.putExtra("collaborationName", collab.getDevNcollabNme());
                intent.putExtra("collaborationId", collab.getCollabId());
                intent.putExtra("isOwner", User.isCurrentUser(UserType.COMPANY, mThisProject.getCompanyId()));
                startActivity(intent);
            }

        }
    }

    @Override
    public void DataArrived(Project project) {
        mThisProject = project;
        txtProjectName.setText(mThisProject.getName());
        txtProjectDescription.setText(mThisProject.getDescription());
        txtCompanyName.setText(mThisProject.getCompanyName());
    }

    @OnClick(R.id.activity_project_display_tvCompanyName)
    public void tvCompanyNameOnClick() {
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("companyId", mThisProject.getCompanyId());
        startActivity(intent);
    }

    @Override
    public void NeededCollaborationsArrived(List<ApiNeededCollaborations> neededCollaborationses) {
        this.neededCollaborations = neededCollaborationses;
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        for (int i = 0; i < neededCollaborationses.size(); i++) {
            items.add(neededCollaborationses.get(i).getDevNcollabNme());
        }
        lvNeededCollaborations.setAdapter(itemsAdapter);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
