package hr.foi.air.solex.activities.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import hr.foi.air.solex.presenters.common.CollaborationPresenter;
import hr.foi.air.solex.presenters.common.CollaborationPresenterImpl;

public class CollaborationActivity extends DrawerActivity implements CollaborationView {

    @BindView(R.id.activity_collaboration_imvDeveloper)
    ImageView imvDeveloper;
    boolean isOwner;
    ApiCompanyCollaborations mThisCollaboration;
    List<String> collaborationSkills;
    CollaborationPresenter mCollaborationPresenter;

    @BindView(R.id.activity_collaboration_lvItSkills)
    ListView lvItSkills;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.activity_collaboration_tvCollaborationDescription)
    TextView tvCollaborationDescription;

    @BindView(R.id.activity_collaboration_tvCollaborationName)
    TextView tvCollaborationName;

    @BindView(R.id.activity_collaboration_tvCompanyProject)
    TextView tvCompanyProject;

    @BindView(R.id.activity_collaboration_tvDeveloperName)
    TextView tvDeveloperName;

    //@BindView(R.id.activity_collaboration_tvSkillsMatch)
    //TextView tvSkillsMatch;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent inti = getIntent();
        Bundle bud = inti.getExtras();
        isOwner = getIntent().getExtras().getBoolean("isOwner");
        int collabId = getIntent().getExtras().getInt("collaborationId");
        String naziv = getIntent().getExtras().getString("collaborationName");
        mCollaborationPresenter = new CollaborationPresenterImpl(this);
        mCollaborationPresenter.getExistingCollaboration(collabId);
        mCollaborationPresenter.getSkillsForCollaboration(collabId);

        lvItSkills.setOnTouchListener(new View.OnTouchListener() {
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
    public void onCollaborationArrived(ApiCompanyCollaborations collaboration) {
        mThisCollaboration = collaboration;
        tvCollaborationDescription.setText(mThisCollaboration.getCollaborationDescription());
        tvCollaborationName.setText(mThisCollaboration.getCollaborationName());
        tvCompanyProject.setText(mThisCollaboration.getProjectName());
        tvDeveloperName.setText(mThisCollaboration.getDevName() + " " + mThisCollaboration.getDevSurname());

    }

    @Override
    public void onSkillsArrived(List<String> skills) {
        collaborationSkills = skills;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, collaborationSkills);

        lvItSkills.setAdapter(adapter);
    }

    @OnClick(R.id.activity_collaboration_imvDeveloper)
    public void imvDeveloperClick(){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId", Integer.parseInt(mThisCollaboration.getDevID()));
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collaboration;
    }
}
