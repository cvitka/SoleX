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

public class ProjectManagementActivity extends AppCompatActivity {
    @BindView(R.id.activity_project_management_lvNeededCollaborations)
    ListView lvNeededCollaborations;

    @BindView(R.id.activity_project_management_btnNewNeededCollaboration)
    Button btnNewNeededCollaboration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_management);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("collab 1");
        items.add("collab 2");
        items.add("collab 3");
        items.add("collab 4");
        lvNeededCollaborations.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_project_management_lvNeededCollaborations)
    public void lvNeededCollaborationsClick(AdapterView<?> adapter, View item, int pos, long id){
        if(pos%2 == 0){
            Intent intent = new Intent(this, CompanyNeededCollaborationActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, CollaborationActivity.class);
            startActivity(intent);

        }

    }

    @OnClick(R.id.activity_project_management_btnNewNeededCollaboration)
    public void btnNewNeededCollaborationClick(){
        Intent intent = new Intent(this, NewNeededCollaborationActivity.class);
        startActivity(intent);

    }
}
