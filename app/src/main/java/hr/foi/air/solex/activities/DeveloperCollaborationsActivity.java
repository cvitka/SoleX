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

public class DeveloperCollaborationsActivity extends DrawerActivity {

    @BindView(R.id.activity_developer_collaborations_lvCollaborations)
    ListView lvCollaborations;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_collaborations;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
