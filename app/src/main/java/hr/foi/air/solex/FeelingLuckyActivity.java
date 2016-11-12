package hr.foi.air.solex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class FeelingLuckyActivity extends AppCompatActivity {
    @BindView(R.id.activity_feeling_lucky_lvLuckyProjects)
    ListView lvLuckyProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_lucky);
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
    }
}
