package hr.foi.air.solex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;

public class FeelingLuckyActivity extends DrawerActivity {
    @BindView(R.id.activity_feeling_lucky_lvLuckyProjects)
    ListView lvLuckyProjects;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feeling_lucky;
    }

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
        lvLuckyProjects.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_feeling_lucky_lvLuckyProjects)
    public void lvLuckyProjectsClick(View view) {
        //open project page
        Intent intent = new Intent(this, ProjectDisplayActivity.class);
        startActivity(intent);
    }
}
