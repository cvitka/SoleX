package hr.foi.air.solex.activities.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

import hr.foi.air.solex.activities.developers.DeveloperProjectsActivity;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.login_registration.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.companies.CompanyCollaborationsActivity;
import hr.foi.air.solex.activities.developers.DeveloperApplicationsActivity;
import hr.foi.air.solex.activities.developers.DeveloperCollaborationsActivity;
import hr.foi.air.solex.activities.companies.CompanyProjectsActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import hr.foi.air.solex.activities.companies.FavouritesActivity;
import hr.foi.air.solex.activities.developers.ProjectSearchActivity;
import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.utils.Utility;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Utility util;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;

    private void startNewActivity(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        //main activities (profile activities) are never finished
        if (User.getInstance().getUserType() == UserType.COMPANY)
            if (getLayoutId() != R.layout.activity_company_profile)
                return;
            else if (User.getInstance().getUserType() == UserType.COMPANY)
                if (getLayoutId() != R.layout.activity_developer_profile)
                    return;
        finish();
    }

    protected static int lastDrawerOption = -1;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        //if we are not already on desired activity
        if (id != lastDrawerOption) {
            Intent intent;
            lastDrawerOption = id; //to be updated with getOptionId in subclasses
            switch (id) {
                case R.id.developer_opt_applications:
                    startNewActivity(DeveloperApplicationsActivity.class);
                    break;
                case R.id.developer_opt_search:
                    startNewActivity(ProjectSearchActivity.class);
                    break;
                case R.id.developer_opt_dev_projects:
                    startNewActivity(DeveloperProjectsActivity.class);
                    break;
                case R.id.developer_opt_collaborations:
                    startNewActivity(DeveloperCollaborationsActivity.class);
                    break;
                case R.id.developer_opt_logout:
                    finish();
                    startNewActivity(LoginActivity.class);
                    break;
                case R.id.developer_opt_profile:
                    intent = new Intent(this, DeveloperProfileActivity.class);
                    intent.putExtra("developerId", User.getInstance().getId());
                    startActivity(intent);
                    break;
                case R.id.company_opt_projects:
                    intent = new Intent(this, CompanyProjectsActivity.class);
                    intent.putExtra("companyId", User.getInstance().getId());
                    startActivity(intent);
                    break;
                case R.id.company_opt_collaborations:
                    startNewActivity(CompanyCollaborationsActivity.class);
                    break;
                case R.id.company_opt_favourites:
                    startNewActivity(FavouritesActivity.class);
                    break;
                case R.id.company_opt_profile:
                    intent = new Intent(this, CompanyProfileActivity.class);
                    intent.putExtra("companyId", User.getInstance().getId());
                    startActivity(intent);
                    break;
                case R.id.company_opt_logout:
                    finish();
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
        setSupportActionBar(mToolbar);

        setSupportActionBar(mToolbar);

        util = new Utility();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setNavigationMenu();

        PreferenceManager.getDefaultSharedPreferences(this)
               .registerOnSharedPreferenceChangeListener(this);

    }

    //sets navigation menu depending on user type
    public void setNavigationMenu() {
        navigationView.setNavigationItemSelectedListener(this);
        if (User.getInstance().getUserType() == UserType.COMPANY) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.company_menu);
        } else if (User.getInstance().getUserType() == UserType.DEVELOPER) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.developer_menu);
        }
    }

    //overridden in subclasses , gets layout id so that this superclass can use it
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_app_preference:
                Intent intent = new Intent(this, AppPreferencesActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Intent intent = getIntent();
        //finish();
        //startActivity(intent);
        recreate();
        (new Utility()).setLanguage(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getIntent().getExtras()!=null)
            for (String key : getIntent().getExtras().keySet()) {
                Object o =  getIntent().getExtras().get(key);

                if (o instanceof Integer) {
                    outState.putInt(key, (int)o);
                }
                else if (o instanceof String) {
                    outState.putString(key, (String)o);
                }
                else if (o instanceof Boolean) {
                    outState.putBoolean(key, (Boolean)o);
                }
                else {
                    // etc.
                }
            }
    }
}
