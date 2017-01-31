package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.CollaborationActivity;
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
    public void lvApplicationstemClick(AdapterView<?> parent, View view, int position, long id){
        //Intent intent = new Intent(this, NeededCollaborationActivity.class);
        //startActivity(intent);
        /** ukoliko je prihvacena aktivnost ucitati mogucnost suradnje inace mogucnost developerovih potrebih suradnji */
        if(mApplicationsList.get(position).getApplicationState().equals("p")){
            Intent intent = new Intent(this, CollaborationActivity.class);
            intent.putExtra("isOwner", false);
            intent.putExtra("collaborationId", mApplicationsList.get(position).getCollaborationId());
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, DeveloperNeededCollaborationActivity.class);
            intent.putExtra("isOwner", false);
            intent.putExtra("collaborationId", mApplicationsList.get(position).getCollaborationId());
            intent.putExtra("applicantsNumber", mApplicationsList.get(position).getApplicatNumber());
            intent.putExtra("companyName", mApplicationsList.get(position).getCompanyName());

            startActivity(intent);
        }
    }

    @Override
    public void onApplicationsArrived(List<CollabApplicat> list) {
        /** popunjavanje listviewa, sa pristiglim podacima */
        mApplicationsList = list;
        mApplicatAdapter = new CollabApplicatAdapter(this, R.layout.list_item_developer_collab_applicat, mApplicationsList, 'a');
        lvApplications.setAdapter(mApplicatAdapter);
    }
}
