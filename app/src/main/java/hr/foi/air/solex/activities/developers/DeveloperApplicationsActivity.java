package hr.foi.air.solex.activities.developers;

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
import hr.foi.air.solex.activities.NeededCollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;

public class DeveloperApplicationsActivity extends DrawerActivity {
    @BindView(R.id.activity_developer_applications_lvApplications)
    ListView lvApplications;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_applications;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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