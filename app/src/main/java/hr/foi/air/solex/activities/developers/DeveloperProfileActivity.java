package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.core.utils.UserType;
import com.example.webservice.Developers.WSRequestDeveloper;
import com.example.webservice.models.Developer;
import com.example.webservice.models.User;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.Listeners.DeveloperDataListener;
import hr.foi.air.solex.activities.ProjectsListingActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.loaders.DLDeveloper;

public class DeveloperProfileActivity extends DrawerActivity implements DeveloperDataListener {

    @BindView(R.id.textView3)
    TextView txtDeveloperAddress;

    @BindView(R.id.textView2)
    TextView txtDevelopeEmail;

    @BindView(R.id.textView)
    TextView txtDevelopeName;

    @BindView(R.id.btnStartUpdateDeveloperData)
    Button btnStartUpdateDeveloperData;

    @BindView(R.id.activity_developer_profile_btnProjects)
    Button btnProjects;

    Developer mThisdDveloper;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if developer is logged in
        if(User.getInstance().getUserType() == UserType.DEVELOPER) {
            //we hide "projects" button that should be visible only to companies
            btnProjects.setVisibility(View.GONE);
            //and we set that developerprofile was last drawer option
            lastDrawerOption = R.id.developer_opt_profile;

            DLDeveloper loader = new DLDeveloper(this);
            WSRequestDeveloper request = new WSRequestDeveloper(loader);
            request.getDeveloperData(Developer.getInstance().getId());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_profile;
    }

    @OnClick(R.id.btnStartUpdateDeveloperData)
    public void btnStartUpdateDeveloperDataClick(View view){
        Intent intent = new Intent(this, UpdateDeveloperDataActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_developer_profile_btnProjects)
    public void btnProjectsClick(){
        Intent intent = new Intent(this, ProjectsListingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void DataArrivedDeveloepr(Developer developer) {
        mThisdDveloper = developer;
        txtDevelopeEmail.setText(mThisdDveloper.getEmail());
        txtDeveloperAddress.setText(mThisdDveloper.getAdresa());
        txtDevelopeName.setText(mThisdDveloper.getIme());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisdDveloper.getEmail());
    }
}
