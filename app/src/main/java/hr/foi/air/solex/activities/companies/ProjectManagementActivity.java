package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.SelectedProjectInteractorImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.CollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.companies.ProjectManagementPresenter;
import hr.foi.air.solex.presenters.companies.ProjectManagementPresenterImpl;

public class ProjectManagementActivity extends DrawerActivity implements ProjectManagementView{

    @BindView(R.id.activity_project_management_lvNeededCollaborations)
    ListView lvNeededCollaborations;

    @BindView(R.id.activity_project_management_tvProjectName)
    TextView txtProjectName;

    @BindView(R.id.activity_project_management_btnNewNeededCollaboration)
    Button btnNewNeededCollaboration;

    @BindView(R.id.activity_project_management_tvDescriptionData)
    TextView txtProjectDescription;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    Project mThisProject;
    ProjectManagementPresenter mPresenter;
    List<ApiNeededCollaborations> neededCollaborations;
    private int projectId;
    boolean isOwner;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_management;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new ProjectManagementPresenterImpl(this,new SelectedProjectInteractorImpl());
      //  mNeededPresenter = new GetNeededCollaborationsPresenterImpl();
        //collabPresenter = new GetNeededCollaborationsPresenterImpl(this, new ApiNeededCollaborationsInteractorImpl());

       // String projectName;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
               /// projectName= null;
            } else {
                //projectName= extras.getString("projectName");
                projectId= extras.getInt("projectId");
                isOwner = extras.getBoolean("isOwner");
            }
        } else {
           // projectName= (String) savedInstanceState.getSerializable("projectName");
        }
        mPresenter.getProject(projectId);
        mPresenter.getNeededCollaboration(projectId);
        //fixes scrolling list inside ScrollView
        lvNeededCollaborations.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @OnItemClick(R.id.activity_project_management_lvNeededCollaborations)
    public void lvNeededCollaborationsClick(AdapterView<?> adapter, View item, int pos, long id){
        ApiNeededCollaborations collab = neededCollaborations.get(pos);
        if(collab.getHasCollaborator() > 0) {
            Intent intent = new Intent(this, CollaborationActivity.class);
            intent.putExtra("collaborationName", collab.getDevNcollabNme());
            intent.putExtra("collaborationId", collab.getCollabId());
            intent.putExtra("isOwner", true);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, CompanyNeededCollaborationActivity.class);
            intent.putExtra("collaborationName", collab.getDevNcollabNme());
            intent.putExtra("collaborationId", collab.getCollabId());
            intent.putExtra("isOwner", true);
            startActivity(intent);
        }
    }

    @OnClick(R.id.activity_project_management_btnNewNeededCollaboration)
    public void btnNewNeededCollaborationClick(){
        Intent intent = new Intent(this, NewNeededCollaborationActivity.class);
        intent.putExtra("projectId",projectId);
        startActivity(intent);
    }

    @Override
    public void DataArrived(Project project) {
        mThisProject = project;
        txtProjectName.setText(mThisProject.getName());
        txtProjectDescription.setText(mThisProject.getDescription());
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
