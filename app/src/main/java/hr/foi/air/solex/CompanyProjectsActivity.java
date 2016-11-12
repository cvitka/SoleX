package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class CompanyProjectsActivity extends AppCompatActivity {
    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;

    @BindView(R.id.myProjectsList)
    ListView listViewMyProjectsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_projects);
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
        Intent intent = new Intent(this, NewProjectActivity.class);
        startActivity(intent);
    }


    @OnItemClick(R.id.myProjectsList)
    public void listViewMyProjectsListClick(){
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        startActivity(intent);
    }
}
