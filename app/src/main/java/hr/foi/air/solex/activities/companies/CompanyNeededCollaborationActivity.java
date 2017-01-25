package hr.foi.air.solex.activities.companies;

import android.content.Intent;
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
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.CollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;

public class CompanyNeededCollaborationActivity extends DrawerActivity implements CompanyNeededCollaborationView{
    @BindView(R.id.activity_company_needed_collaboration_lvApplicants)
    ListView lvApplicants;

    @BindView(R.id.activity_company_needed_collaboration_btnAcceptApplicant)
    Button btnAcceptApplicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

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

    @Override
    public void onApplicantsArrived() {

    }

    @Override
    public void onSkillsListArrived() {

    }

    @Override
    public void onSuccessfullAssign() {

    }
}
