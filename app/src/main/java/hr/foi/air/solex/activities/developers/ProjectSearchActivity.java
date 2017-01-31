package hr.foi.air.solex.activities.developers;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.fragments.ProjectsSearchFeelingLuckyFragment;
import hr.foi.air.solex.fragments.ProjectsSearchMainFragment;
import hr.foi.air.solex.fragments.ProjectsSearchResultFragment;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.mdevelopers.Developer;
import hr.foi.air.solex.presenters.developers.ProjectSearchPreseneter;
import hr.foi.air.solex.presenters.developers.ProjectSearchPresenterImpl;

public class ProjectSearchActivity extends DrawerActivity {

    ProjectsSearchMainFragment projectsSearchMainFragment;

    @BindView(R.id.content_frame)
    RelativeLayout frameLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        /** ucitavanje fragmenta */
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, projectsSearchMainFragment.newInstance());
        fragmentTransaction.commit();

    }
}
