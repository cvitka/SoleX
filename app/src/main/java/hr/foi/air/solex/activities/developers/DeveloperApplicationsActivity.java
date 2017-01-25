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
import hr.foi.air.solex.activities.NeededCollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.CollabApplicatAdapter;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.presenters.developers.DeveloperApplicationsPresenter;
import hr.foi.air.solex.presenters.developers.DeveloperApplicationsPresenterImpl;

public class DeveloperApplicationsActivity extends DrawerActivity implements DeveloperApplicationsView{
    @BindView(R.id.activity_developer_applications_lvApplications)
    ListView lvApplications;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_applications;
    }

    DeveloperApplicationsPresenter mApplicationsPresenter;

    List<CollabApplicat> mApplicationsList;

    CollabApplicatAdapter mApplicatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mApplicationsPresenter = new DeveloperApplicationsPresenterImpl(this);
        mApplicationsPresenter.getApplications(User.getInstance().getId());
    }

    @OnItemClick(R.id.activity_developer_applications_lvApplications)
    public void lvApplicationstemClick(View view){
        Intent intent = new Intent(this, NeededCollaborationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onApplicationsArrived(List<CollabApplicat> list) {
        mApplicationsList = list;
        mApplicatAdapter = new CollabApplicatAdapter(this, R.layout.list_item_developer_collab_applicat, mApplicationsList, 'a');
        lvApplications.setAdapter(mApplicatAdapter);
    }
}
