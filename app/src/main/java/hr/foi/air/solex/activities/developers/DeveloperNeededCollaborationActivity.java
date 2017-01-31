package hr.foi.air.solex.activities.developers;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.presenters.developers.DeveloperNeededCollaborationPresenter;
import hr.foi.air.solex.presenters.developers.DeveloperNeededCollaborationPresenterImpl;
import hr.foi.air.solex.utils.Utility;

public class DeveloperNeededCollaborationActivity extends DrawerActivity implements DeveloperNeededCollaborationView{
    @BindView(R.id.activity_developer_needed_collaboration_btnApplicate)
    Button btnApplicate;
    @BindView(R.id.activity_developer_needed_collaboration_lvSkills)
    ListView lvSkills;
    @BindView(R.id.activity_developer_needed_collaboration_tvCollaborationDetails)
    TextView tvCollaborationDetails;
    @BindView(R.id.activity_developer_needed_collaboration_tvCollaborationName)
    TextView tvCollaborationName;
    @BindView(R.id.activity_developer_needed_collaboration_tvProjectName)
    TextView tvProjectName;
    @BindView(R.id.activity_developer_needed_collaboration_tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.activity_developer_needed_collaboration_tvApplicantsNumber)
    TextView tvApplicantsNumber;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_needed_collaboration;
    }


    NeededCollaborationData mThisCollaboration;
    DeveloperNeededCollaborationPresenter mPresenter;

    private String companyName;
    private int applicantsNumber;
    private int companyId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new DeveloperNeededCollaborationPresenterImpl(this);
        int collaborationId;
        if (savedInstanceState == null) {
            companyName = getIntent().getExtras().getString("companyName");
            applicantsNumber = getIntent().getExtras().getInt("applicantsNumber");
            collaborationId = getIntent().getExtras().getInt("collaborationId");
        }
        else {
            companyName = savedInstanceState.getString("companyName");
            applicantsNumber = savedInstanceState.getInt("applicantsNumber");
            collaborationId = savedInstanceState.getInt("collaborationId");
        }

        mPresenter.getCollaborationSkills(collaborationId);
        mPresenter.getCollaborationData(collaborationId, User.getInstance().getId());

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

    @OnClick(R.id.activity_developer_needed_collaboration_btnApplicate)
    void btnApplicateOnClick(){
        int state =  mThisCollaboration.getState();
        if(state==0){
            mPresenter.apply(mThisCollaboration.getCollaborationId(), User.getInstance().getId());
            mThisCollaboration.setState(1);
        }
        else{
            mPresenter.removeApply(mThisCollaboration.getCollaborationId(), User.getInstance().getId());
            mThisCollaboration.setState(0);
        }
        ChangeBtnApplicate(mThisCollaboration.getState());
    }

    private void ChangeBtnApplicate(int state){
        if(state==0){
            btnApplicate.setText(getResources().getString(R.string.applicate));
        }
        else if(state==1){
            btnApplicate.setText(getResources().getString(R.string.withdraw));
        }
    }

    @Override
    public void onCollaborationDataArrived(NeededCollaborationData collaborationData) {
        mThisCollaboration = collaborationData;
        tvCollaborationDetails.setText(mThisCollaboration.getCollaborationDescription());
        tvCollaborationName.setText(mThisCollaboration.getCollaborationName());
        tvCompanyName.setText(companyName);
        tvProjectName.setText(mThisCollaboration.getProjectName());
        tvApplicantsNumber.setText(String.valueOf(applicantsNumber));
        companyId = collaborationData.getCompanyId();
        ChangeBtnApplicate(mThisCollaboration.getState());
    }

    @Override
    public void onSkillsArrived(List<String> Skills) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Skills);
        lvSkills.setAdapter(adapter);
        Utility.setListViewHeightBasedOnChildren(lvSkills, 6);
    }

    @Override
    public void onApplySuccessfull() {
        mPresenter.pushNotif(companyId);

    }

    @Override
    public void onRemoveApplySucessfull() {

    }

    @Override
    public void pushSucessful() {
    }
}
