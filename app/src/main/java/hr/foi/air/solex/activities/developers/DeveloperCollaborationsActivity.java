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
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.presenters.developers.DeveloperCollaborationsPresenter;
import hr.foi.air.solex.presenters.developers.DeveloperCollaborationsPresenterImpl;

public class DeveloperCollaborationsActivity extends DrawerActivity implements DeveloperCollaborationsView {

    @BindView(R.id.activity_developer_collaborations_lvCollaborations)
    ListView lvCollaborations;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_collaborations;
    }

    DeveloperCollaborationsPresenter mCollaborationsPresenter;
    List<CollabApplicat> mCollaborationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("collaboration 1");
        items.add("collaboration 2");
        items.add("collaboration 3");
        items.add("collaboration 4");
        lvCollaborations.setAdapter(itemsAdapter);
        this.mCollaborationsPresenter = new DeveloperCollaborationsPresenterImpl(this);
        mCollaborationsPresenter.getCollaborations(User.getInstance().getId());
    }

    @OnItemClick(R.id.activity_developer_collaborations_lvCollaborations)
    public void lvCollaborationsClick(View view){
        Intent intent = new Intent(this, CollaborationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCollaborationsArrived(List<CollabApplicat> list) {
        mCollaborationsList = list;
    }
}
