package hr.foi.air.solex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.core.utils.UserType;
import com.example.webservice.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawerActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    @BindView(R.id.nav_view)
    NavigationView  navigationView;

    private void startNewActivity(Class<?> activity){
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        //main activities (profile activities) are never finished
        if(User.getInstance().getUserType() == UserType.COMPANY)
            if(getLayoutId() != R.layout.activity_company_profile)
                return;
        else if(User.getInstance().getUserType() == UserType.COMPANY)
            if(getLayoutId() != R.layout.activity_developer_profile)
                return;
        finish();
    }

    protected static int lastDrawerOption = -1;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //if we are not already on desired activity
        if(id != lastDrawerOption) {
            lastDrawerOption = id; //to be updated with getOptionId in subclasses
            switch(id)
            {
                case R.id.developer_opt_applications:
                    startNewActivity(DeveloperApplicationsActivity.class);
                    break;
                case R.id.developer_opt_search:
                    startNewActivity(ProjectSearchActivity.class);
                    break;
                case R.id.developer_opt_collaborations:
                    startNewActivity(DeveloperCollaborationsActivity.class);
                    break;
                case R.id.developer_opt_logout:
                    startNewActivity(LoginActivity.class);
                    break;
                case R.id.developer_opt_profile:
                    startNewActivity(DeveloperProfileActivity.class);
                    break;
                case R.id.company_opt_projects:
                    startNewActivity(CompanyProjectsActivity.class);
                    break;
                case R.id.company_opt_collaborations:
                    startNewActivity(CompanyCollaborationsActivity.class);
                    break;
                case R.id.company_opt_favourites:
                    startNewActivity(FavouritesActivity.class);
                    break;
                case R.id.company_opt_profile:
                    startNewActivity(CompanyProfileActivity.class);
                    break;
                case R.id.company_opt_logout:
                    startNewActivity(LoginActivity.class);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        setNavigationMenu();
    }

     //sets navigation menu depending on user type
    public void setNavigationMenu(){
        navigationView.setNavigationItemSelectedListener(this);
        if(User.getInstance().getUserType() == UserType.COMPANY) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.company_menu);
        }
        else if(User.getInstance().getUserType() == UserType.DEVELOPER){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.developer_menu);
        }
    }
    //overridden in subclasses , gets layout id so that this superclass can use it
     protected int getLayoutId(){
             return R.layout.activity_drawer;
         }
}
