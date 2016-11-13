package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ProjectsListingActivity extends DrawerActivity {

    @BindView(R.id.activity_projects_listing_lvProjects)
    ListView lvProjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("project 1");
        items.add("project 2");
        items.add("project 3");
        items.add("project 4");
        lvProjects.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_projects_listing_lvProjects)
    public void lvProjectsClick(View view){
        Intent intent = new Intent(this, ProjectDisplayActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_projects_listing;
    }

}
