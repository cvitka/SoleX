package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
    public void lvCollaborationsClick(AdapterView<?> parent, View view, int position, long id){
        Intent intent = new Intent(this, CollaborationActivity.class);
        intent.putExtra("isOwner", false);
        intent.putExtra("collaborationId", mCollaborationsList.get(position).getCollaborationId());
        startActivity(intent);
    }

    @Override
    public void onCollaborationsArrived(List<CollabApplicat> list) {
        mCollaborationsList = list;

        mCollabAdapter = new CollabApplicatAdapter(this, R.layout.list_item_developer_collaborations, mCollaborationsList, 'c', new CollabApplicatAdapter.ClickListener() {
            @Override
            public void onRatingChanged(CollabApplicat collab, int rating) {
                mCollaborationsPresenter.rate(rating, collab.getCompanyId(), collab.getCollaborationId());
            }
        });
        lvCollaborations.setAdapter(mCollabAdapter);
    }

    @Override
    public void onRateSucceeded() {
        Toast.makeText(getApplicationContext(), R.string.collab_rated , Toast.LENGTH_SHORT).show();
    }
}
