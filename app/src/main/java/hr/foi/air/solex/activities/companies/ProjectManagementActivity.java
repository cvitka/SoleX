package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.collaboration.ApiNeededCollaborations;
import com.example.webservice.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import com.example.webservice.models.projects.Project;
import com.example.webservice.models.projects.SelectedProjectInteractorImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.CollaborationActivity;
import hr.foi.air.solex.activities.CompanyNeededCollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.GetNeededCollaaborationsPresenter;
import hr.foi.air.solex.presenters.GetNeededCollaborationsPresenterImpl;
import hr.foi.air.solex.presenters.ProjectManagementPresenter;
import hr.foi.air.solex.presenters.ProjectManagementPresenterImpl;

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

    Project mThisProject;
    ProjectManagementPresenter mPresenter;
    GetNeededCollaaborationsPresenter colabPresenter;
    private String projectId;
    private ArrayList<String> itemsId = new ArrayList<String>();
    private String selectedProjectName;
    private String selectedProjectId;
    private int projectID;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_management;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresenter = new ProjectManagementPresenterImpl(this,new SelectedProjectInteractorImpl());
        colabPresenter = new GetNeededCollaborationsPresenterImpl(this, new ApiNeededCollaborationsInteractorImpl());

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("collab (needed)");
        items.add("collab (filled)");
        items.add("collab (needed)");
        items.add("collab (filled)");
        lvNeededCollaborations.setAdapter(itemsAdapter);

        String projectName;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                projectName= null;
                projectId = null;
            } else {
                projectName= extras.getString("projectName");
                projectId= extras.getString("projectId");
                projectID = Integer.parseInt(projectId);
                mPresenter.getProject(projectID);
                colabPresenter.getNeededCollaboration(projectID);
            }
        } else {
            projectName= (String) savedInstanceState.getSerializable("projectName");
        }
    }

    @OnItemClick(R.id.activity_project_management_lvNeededCollaborations)
    public void lvNeededCollaborationsClick(AdapterView<?> adapter, View item, int pos, long id){
        if(pos%2 == 0){
            Intent intent = new Intent(this, CompanyNeededCollaborationActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, CollaborationActivity.class);
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
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        for (int i = 0; i < neededCollaborationses.size(); i++) {
            items.add(neededCollaborationses.get(i).getDevNcollabNme());
            itemsId.add(neededCollaborationses.get(i).getCollabId());

        }
        lvNeededCollaborations.setAdapter(itemsAdapter);
        lvNeededCollaborations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProjectName = ((TextView) view).getText().toString();
                Integer selectionID = Integer.parseInt(itemsId.get(i));
                selectedProjectId = selectionID.toString();
                onSelect(selectedProjectName,selectedProjectId);

            }
        });
    }

    public void onSelect(String name,String id){
        Intent intent = new Intent(this, CollaborationActivity.class);
        intent.putExtra("collaborationName",name);
        intent.putExtra("collaborationId",id);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
