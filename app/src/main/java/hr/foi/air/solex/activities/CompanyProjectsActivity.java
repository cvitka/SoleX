package hr.foi.air.solex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.companies.AddNewProjectActivity;
import hr.foi.air.solex.activities.companies.ProjectManagementActivity;

public class CompanyProjectsActivity extends DrawerActivity {
    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;

    @BindView(R.id.myProjectsList)
    ListView listViewMyProjectsList;

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
    }

    @OnClick(R.id.btnAddNewProject)
    public void btnAddNewProjectClick(){
        Intent intent = new Intent(this, AddNewProjectActivity.class);
        startActivity(intent);
    }


    @OnItemClick(R.id.myProjectsList)
    public void listViewMyProjectsListClick(){
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        startActivity(intent);
    }
}
