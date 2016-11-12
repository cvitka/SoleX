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

public class DeveloperApplicationsActivity extends AppCompatActivity {
    @BindView(R.id.activity_developer_applications_lvApplications)
    ListView lvApplications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_applications);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("application 1");
        items.add("application 2");
        items.add("application 3");
        items.add("application 4");
        lvApplications.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_developer_applications_lvApplications)
    public void lvApplicationstemClick(View view){
        Intent intent = new Intent(this, NeededCollaborationActivity.class);
        startActivity(intent);
    }
}
