package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ProjectDisplayActivity extends DrawerActivity {
    @BindView(R.id.activity_project_display_lvCollaborations)
    ListView lvCollaborations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("collaboration (needed)");
        items.add("collaboration (needed)");
        items.add("collaboration (filled)");
        items.add("collaboration (needed)");
        lvCollaborations.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_project_display_lvCollaborations)
    public void btnAddNewProjectClick(AdapterView<?> adapter, View item, int pos, long id){
        if(pos == 0 || pos == 1 || pos ==3){
            Intent intent = new Intent(this, NeededCollaborationActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, CollaborationActivity.class);
            startActivity(intent);

        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_display;
    }
}
