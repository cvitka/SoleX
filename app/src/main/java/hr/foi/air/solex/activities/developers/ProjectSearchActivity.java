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

public class ProjectSearchActivity extends DrawerActivity implements SensorEventListener, ProjectsSearchFeelingLuckyFragment.ReturnToPreviousListener {

    SensorManager mSensorManager;
    private Sensor mSensor;
    private Vibrator mVibrator;
    private long mShakeTimestamp;

    ProjectsSearchFeelingLuckyFragment mProjectsSearchFeelingLuckyFragment;
    ProjectsSearchMainFragment projectsSearchMainFragment;

    ArrayList<String> allSkills;
    ArrayList<String> devSkills;

    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.3F;

    boolean dataArrived = false;

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

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, projectsSearchMainFragment.newInstance());
        fragmentTransaction.commit();


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        mVibrator = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);

        mProjectsSearchFeelingLuckyFragment = new ProjectsSearchFeelingLuckyFragment();
        mProjectsSearchFeelingLuckyFragment.newInstance();
        mProjectsSearchFeelingLuckyFragment.setListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
//cujem ja teb
        //hahahaha pa kako ne cujes ja kad pricam gledaj speakers sto ide gore
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);
            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();
                final long time = mShakeTimestamp + SHAKE_SLOP_TIME_MS;
                Log.d("time", Float.toString(time));
                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }
                mShakeTimestamp = now;
                mSensorManager.unregisterListener(this);
                mVibrator.vibrate(500);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, ProjectsSearchFeelingLuckyFragment.newInstance());
                fragmentTransaction.addToBackStack(" ProjectSearchMainFragment");
                fragmentTransaction.commit();

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void activateShakeSensor() {
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
