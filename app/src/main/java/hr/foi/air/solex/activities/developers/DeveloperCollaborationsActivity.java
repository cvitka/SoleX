package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.CollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.CollabApplicatAdapter;
import hr.foi.air.solex.adapters.ProjectsListAdapter;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.presenters.developers.DeveloperCollaborationsPresenter;
import hr.foi.air.solex.presenters.developers.DeveloperCollaborationsPresenterImpl;
import hr.foi.air.solex.utils.UserType;

public class DeveloperCollaborationsActivity extends DrawerActivity implements DeveloperCollaborationsView {

    @BindView(R.id.activity_developer_collaborations_lvCollaborations)
    ListView lvCollaborations;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_collaborations;
    }

    DeveloperCollaborationsPresenter mCollaborationsPresenter;
    List<CollabApplicat> mCollaborationsList;
    CollabApplicatAdapter mCollabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mCollaborationsPresenter = new DeveloperCollaborationsPresenterImpl(this);
        mCollaborationsPresenter.getCollaborations(User.getInstance().getId());
    }

    @OnItemClick(R.id.activity_developer_collaborations_lvCollaborations)
    public void lvCollaborationsClick(View view){
        //Intent intent = new Intent(this, CollaborationActivity.class);
        //startActivity(intent);
    }

    @Override
    public void onCollaborationsArrived(List<CollabApplicat> list) {
        mCollaborationsList = list;

        //lvCollaborations.setOnItemClickListener(this);
        mCollabAdapter = new CollabApplicatAdapter(this, R.layout.list_item_developer_collab_applicat, mCollaborationsList, 'c');
        lvCollaborations.setAdapter(mCollabAdapter);
    }
}
