package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class CompanyNeededCollaborationActivity extends DrawerActivity {
    @BindView(R.id.activity_company_needed_collaboration_lvApplicants)
    ListView lvApplicants;

    @BindView(R.id.activity_company_needed_collaboration_btnAcceptApplicant)
    Button btnAcceptApplicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("developer 1");
        items.add("developer 2");
        items.add("developer 3");
        items.add("developer 4");
        lvApplicants.setAdapter(itemsAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_needed_collaboration;
    }

    @OnItemClick(R.id.activity_company_needed_collaboration_lvApplicants)
    public void lvApplicantsItemClick(View view){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_company_needed_collaboration_btnAcceptApplicant)
    public void btnAcceptApplicantClick(){
        Intent intent = new Intent(this, CollaborationActivity.class);
        startActivity(intent);
    }
}
