package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.CollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import hr.foi.air.solex.adapters.ApplicantsAdapter;
import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;
import hr.foi.air.solex.presenters.companies.CompanyNeededCollaborationPresenter;
import hr.foi.air.solex.presenters.companies.CompanyNeededCollaborationPresenterImpl;
import hr.foi.air.solex.utils.Utility;

public class CompanyNeededCollaborationActivity extends DrawerActivity implements CompanyNeededCollaborationView{



    @BindView(R.id.activity_company_needed_collaboration_tvCollabName)
    TextView tvCollabName;

    @BindView(R.id.activity_company_needed_collaboration_tvProjectName)
    TextView tvProjectName;

    @BindView(R.id.activity_company_needed_collaboration_lvApplicants)
    ListView lvApplicants;

    @BindView(R.id.activity_company_needed_collaboration_lvItSkills)
    ListView lvSkills;

    @BindView(R.id.activity_company_needed_collaboration_btnAcceptApplicant)
    Button btnAcceptApplicant;


    @BindView(R.id.activity_company_profile_scrollView)
    ScrollView scrollView;

    List<Applicant> mApplicantList;
    List<String> mSkillsList;
    NeededCollaborationData mThisCollaboration;

    CompanyNeededCollaborationPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        int collaborationId = getIntent().getExtras().getInt("collaborationId");

        mPresenter = new CompanyNeededCollaborationPresenterImpl(this);

        mPresenter.getCollaborationData(collaborationId);
        mPresenter.getApplicants(collaborationId);
        mPresenter.getCollaborationSkills(collaborationId);

        lvApplicants.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lvSkills.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_needed_collaboration;
    }

    @OnItemClick(R.id.activity_company_needed_collaboration_lvApplicants)
    public void lvApplicantsItemClick(View view){
        //Intent intent = new Intent(this, DeveloperProfileActivity.class);
        //startActivity(intent);

    }

    @OnClick(R.id.activity_company_needed_collaboration_btnAcceptApplicant)
    public void btnAcceptApplicantClick(){
        //Intent intent = new Intent(this, CollaborationActivity.class);
        //startActivity(intent);
    }

    @Override
    public void onApplicantsArrived(List<Applicant> list) {
        mApplicantList = list;
        //lvApplicants.setOnItemClickListener(this);
        ApplicantsAdapter adapter = new ApplicantsAdapter(
                this,
                R.layout.list_item_applicant,
                mApplicantList);
        lvApplicants.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(lvApplicants, 4);
    }

    @Override
    public void onSkillsListArrived(List<String> list) {
        mSkillsList = list;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSkillsList);
        lvSkills.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(lvSkills, 5);
    }

    @Override
    public void onCollaborationDataArrived(NeededCollaborationData collaboration) {
        mThisCollaboration = collaboration;
        tvCollabName.setText(mThisCollaboration.getCollaborationName());
        tvProjectName.setText(mThisCollaboration.getProjectName());
    }

    @Override
    public void onSuccessfullAssign() {

    }
}
