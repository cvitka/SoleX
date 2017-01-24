package hr.foi.air.solex.activities.common;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.NeededCollaborationActivity;
import hr.foi.air.solex.adapters.ProjectsListAdapter;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.presenters.common.ProjectListingPresenter;
import hr.foi.air.solex.presenters.common.ProjectListingPresenterImpl;
import hr.foi.air.solex.utils.UserType;

public class ProjectListingActivity extends DrawerActivity implements ProjectListingView{
    @BindView(R.id.activity_projects_listing_lvProjects)
    ListView lvProjects;

    @BindView(R.id.activity_projects_listing_tvListLabel)
    TextView tvListLabel;

    UserType projectsOwnerType;
    int projectsOwnerId;
    String projectsOwnerName;
    List<ProfileScreenProject> mProjectsList;
    ProjectListingPresenter mProjectListingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProjectListingPresenter = new ProjectListingPresenterImpl(this);
        int type = getIntent().getExtras().getInt("type");
        if(type == UserType.COMPANY.intVal())
            projectsOwnerType = UserType.COMPANY;
        else
            projectsOwnerType = UserType.DEVELOPER;

        projectsOwnerId = getIntent().getExtras().getInt("ownerId");
        projectsOwnerName = getIntent().getExtras().getString("ownerName");

        tvListLabel.setText(projectsOwnerName + "'s projects list" + "");//dodao "" samo da ne javlja retardirani error
        mProjectListingPresenter.getProjects(projectsOwnerId, projectsOwnerType);
    }

    @OnItemClick(R.id.activity_projects_listing_lvProjects)
    public void lvProjectsItemClick(AdapterView<?> adapter, View item, int pos, long id){
        ProfileScreenProject clickedProj = mProjectsList.get(pos);
        Intent intent = new Intent(this, ProjectDisplayActivity.class);
        intent.putExtra("projectId", clickedProj.getId());
        startActivity(intent);
    }

    @Override
    public void onProjectsArrived(List<ProfileScreenProject> projects) {

        mProjectsList = projects;
        ProjectsListAdapter adapter =
                new ProjectsListAdapter(this, android.R.layout.simple_list_item_1, mProjectsList, UserType.COMPANY);

        lvProjects.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_projects_listing;
    }
}