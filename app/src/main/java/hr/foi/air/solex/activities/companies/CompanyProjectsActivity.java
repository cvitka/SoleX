package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.webservice.models.projects.ApiProject;
import com.example.webservice.models.projects.ProjectInteractorImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.ProjectsAdapter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenterImpl;

public class CompanyProjectsActivity extends DrawerActivity implements CompanyProjectsView {

    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;

    @BindView(R.id.myProjectsList)
    ListView listViewMyProjectsList;


    CompanyProjectsPresenter mCompanyProjectsPresenter;
    private RecyclerView recyclerView;
    private ProjectsAdapter projectsAdapter;
    private ArrayList<String> itemsId = new ArrayList<String>();
    private String selectedProjectName;
    private String selectedProjectId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_projects;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("projekt 1");
        items.add("projekt 2");
        items.add("projekt 3");
        items.add("projekt 4");
        listViewMyProjectsList.setAdapter(itemsAdapter);

        this.mCompanyProjectsPresenter = new CompanyProjectsPresenterImpl(this, new ProjectInteractorImpl());
        mCompanyProjectsPresenter.getProjects();
    }

    @OnClick(R.id.btnAddNewProject)
    public void btnAddNewProjectClick() {
        Intent intent = new Intent(this, AddNewProjectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataArrived(List<ApiProject> projects) {

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        for (int i = 0; i < projects.size(); i++) {
            items.add(projects.get(i).getNaziv());
            itemsId.add(projects.get(i).getProjektiId());

        }
        listViewMyProjectsList.setAdapter(itemsAdapter);
        listViewMyProjectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("projectName",name);
        intent.putExtra("projectId",id);
        startActivity(intent);
    }
}
