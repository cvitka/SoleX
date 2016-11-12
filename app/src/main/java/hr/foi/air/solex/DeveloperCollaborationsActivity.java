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

public class DeveloperCollaborationsActivity extends AppCompatActivity {

    @BindView(R.id.activity_developer_collaborations_lvCollaborations)
    ListView lvCollaborations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_collaborations);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("collaboration 1");
        items.add("collaboration 2");
        items.add("collaboration 3");
        items.add("collaboration 4");
        lvCollaborations.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_developer_collaborations_lvCollaborations)
    public void lvCollaborationsClick(View view){
        Intent intent = new Intent(this, CollaborationActivity.class);
        startActivity(intent);
    }
}
