package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ProjectSearchActivity extends AppCompatActivity {
    @BindView(R.id.txtViewFeelingLucky)
    TextView txtViewFeelingLucky;

    @BindView(R.id.activity_project_search_lvSearchedProjects)
    ListView lvSearchedProjects;

    @BindView(R.id.activity_project_search_lvItSkills)
    ListView lvItSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_search);
        ButterKnife.bind(this);
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("project 1");
        items.add("project 2");
        items.add("project 3");
        items.add("project 4");
        lvSearchedProjects.setAdapter(itemsAdapter);


        ArrayList<String> items2 = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter2;
        itemsAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items2);
        items2.add("skill 1");
        items2.add("skill 2");
        items2.add("skill 3");
        items2.add("skill 4");
        lvItSkills.setAdapter(itemsAdapter2);
    }

    @OnItemClick(R.id.activity_project_search_lvSearchedProjects)
    public void lvSearchedProjectsClick(View view) {
        //open project page
    }

    @OnClick(R.id.txtViewFeelingLucky)
    public void txtViewFeelingLuckyClick(){
        Intent intent = new Intent(this, FeelingLuckyActivity.class);
        startActivity(intent);
    }
}
