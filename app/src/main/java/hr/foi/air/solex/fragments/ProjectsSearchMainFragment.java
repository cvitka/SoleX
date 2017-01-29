package hr.foi.air.solex.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.searched_project.SearchProjects;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.presenters.developers.ProjectSearchPreseneter;
import hr.foi.air.solex.presenters.developers.ProjectSearchPresenterImpl;

public class ProjectsSearchMainFragment extends Fragment implements ProjectSearchView, SensorEventListener {
    @BindView(R.id.activity_project_search_lvItSkills)
    ListView lvProjectSearchITSkills;

    @BindView(R.id.activity_project_search_actvNewSkill)
    AutoCompleteTextView actvNewSkill;

    @BindView(R.id.activity_project_search_etAddPercentage)
    EditText etAddPercentage;


    SensorManager mSensorManager;
    private Sensor mSensor;
    private Vibrator mVibrator;
    private long mShakeTimestamp;
    private Handler handler;

    private List<String> developerSkillsList;
    private List<String> allSkillsList;
    private ArrayAdapter<String> mDevSkillAdapter;

    ProjectSearchPreseneter projectSearchPreseneter;
    ProjectsSearchFeelingLuckyFragment mProjectsSearchFeelingLuckyFragment;

    boolean dataArrived = false;
    public static final String PROJECT_INFO = "PROJECT_INFO";

    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 1.3F;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectSearchPreseneter = new ProjectSearchPresenterImpl(this);
        projectSearchPreseneter.getAllSkillList();
        projectSearchPreseneter.getSkillList(User.getInstance().getId());

        //aktiviranje sensora
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //vibriraj nakon shakea
        mVibrator = (Vibrator) getActivity().getSystemService(getContext().VIBRATOR_SERVICE);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_projects_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static ProjectsSearchMainFragment newInstance() {
        return new ProjectsSearchMainFragment();
    }

    @OnClick(R.id.btnSearchCollaborations)
    public void onSearch() {
        ProjectsSearchResultFragment fragment = new ProjectsSearchResultFragment();
        if (etAddPercentage.getText().toString().isEmpty() || etAddPercentage.getText().toString() == null) {
            Toast.makeText(getActivity(), R.string.enter_percentage, Toast.LENGTH_LONG).show();
        } else if (Integer.parseInt(etAddPercentage.getText().toString()) > 100) {
            Toast.makeText(getActivity(), R.string.percentage_limitation, Toast.LENGTH_LONG).show();
        } else {
            //prijenos objekta na result(lista i int)
            Bundle bundle = new Bundle();
            SearchProjects searchProjects = new SearchProjects();
            searchProjects.setPercentage(Integer.parseInt(etAddPercentage.getText().toString()));
            searchProjects.setSkills(developerSkillsList);
            bundle.putParcelable(PROJECT_INFO, searchProjects);
            bundle.putInt("numberOfSearchedSkills", developerSkillsList.size());
            fragment.setArguments(bundle);

            mSensorManager.unregisterListener(this);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(" ProjectsSearchMainFragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void allSkillsListArrived(List<String> list) {
        allSkillsList = list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, list);
        actvNewSkill.setAdapter(adapter);
        dataArrived = true;
    }

    @Override
    public void developerSkillsListArrived(List<String> list) {
        developerSkillsList = list;
        mDevSkillAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lvProjectSearchITSkills.setAdapter(mDevSkillAdapter);
        dataArrived = true;
    }

    @OnClick(R.id.activity_project_search_ibAddSkills)
    public void onClick() {
        String skill = actvNewSkill.getText().toString();
        if (skill.isEmpty()) {
            Toast.makeText(getActivity(), R.string.choose_skill, Toast.LENGTH_LONG).show();
        } else if (developerSkillsList.contains(skill)) {
            Toast.makeText(getActivity(), R.string.error_skill_exists, Toast.LENGTH_LONG).show();
        } else if (!allSkillsList.contains(skill)) {
            Toast.makeText(getActivity(), R.string.no_such_skill, Toast.LENGTH_LONG).show();
        } else {
            developerSkillsList.add(skill);
            mDevSkillAdapter.notifyDataSetChanged();
            actvNewSkill.setText("");
        }
    }

    @OnItemLongClick(R.id.activity_project_search_lvItSkills)
    public boolean onItemLongClick(AdapterView<?> parent, int position) {
        developerSkillsList.remove(position);
        mDevSkillAdapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
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

                if (mShakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return;
                }
                mShakeTimestamp = now;
                mSensorManager.unregisterListener(this);
                mVibrator.vibrate(500);
                transferDataFeelingLucky();

            }
        }
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void transferDataFeelingLucky() {
        mProjectsSearchFeelingLuckyFragment = new ProjectsSearchFeelingLuckyFragment();

        Bundle bundle = new Bundle();
        SearchProjects searchProjects = new SearchProjects();

        searchProjects.setSkills(developerSkillsList);
        bundle.putParcelable(PROJECT_INFO, searchProjects);
        mProjectsSearchFeelingLuckyFragment.setArguments(bundle);

        handler = new Handler();
        mSensorManager.unregisterListener(this);

        handler.post(new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, mProjectsSearchFeelingLuckyFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        if (dataArrived == true) {
            //popunjavanje lista nakon povratka sa FeelingLucky i Result
            mDevSkillAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, developerSkillsList);
            lvProjectSearchITSkills.setAdapter(mDevSkillAdapter);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, allSkillsList);
            actvNewSkill.setAdapter(adapter);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}

