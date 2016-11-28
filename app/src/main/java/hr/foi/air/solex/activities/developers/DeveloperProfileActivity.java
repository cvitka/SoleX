package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.core.utils.UserType;
import com.example.webservice.models.User;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.ProjectsListingActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;

public class DeveloperProfileActivity extends DrawerActivity {

    @BindView(R.id.btnStartUpdateDeveloperData)
    Button btnStartUpdateDeveloperData;

    @BindView(R.id.activity_developer_profile_btnProjects)
    Button btnProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if developer is logged in
        if(User.getInstance().getUserType() == UserType.DEVELOPER) {
            //we hide "projects" button that should be visible only to companies
            btnProjects.setVisibility(View.GONE);
            //and we set that developerprofile was last drawer option
            lastDrawerOption = R.id.developer_opt_profile;
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
}
