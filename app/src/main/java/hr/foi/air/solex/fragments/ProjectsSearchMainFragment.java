package hr.foi.air.solex.fragments;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class ProjectsSearchMainFragment extends Fragment implements ProjectSearchView {
    @BindView(R.id.activity_project_search_lvItSkills)
    ListView lvProjectSearchITSkills;

    @BindView(R.id.activity_project_search_actvNewSkill)
    AutoCompleteTextView actvNewSkill;

    @BindView(R.id.activity_project_search_etAddPercentage)
    EditText etAddPercentage;

    List<String> developerSkillsList;
    private List<String> allSkillsList;
    private ArrayAdapter<String> mDevSkillAdapter;
    private SensorManager mSensorManager;
    ProjectSearchPreseneter projectSearchPreseneter;
    public static final String PROJECT_INFO = "PROJECT_INFO";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectSearchPreseneter = new ProjectSearchPresenterImpl(this);
        projectSearchPreseneter.getAllSkillList();
        projectSearchPreseneter.getSkillList(User.getInstance().getId());
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
        Integer mPercentage = Integer.parseInt(etAddPercentage.getText().toString());

        ProjectsSearchResultFragment fragment = new ProjectsSearchResultFragment();
        Bundle bundle = new Bundle();
        SearchProjects searchProjects = new SearchProjects();
        searchProjects.setPercentage(mPercentage);
        searchProjects.setSkills(developerSkillsList);
        bundle.putParcelable(PROJECT_INFO, searchProjects);
        fragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.addToBackStack(" ProjectsSearchMainFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void allSkillsListArrived(List<String> list) {
        allSkillsList = list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, list);
        actvNewSkill.setAdapter(adapter);
    }

    @Override
    public void developerSkillsListArrived(List<String> list) {
        developerSkillsList = list;
        mDevSkillAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        lvProjectSearchITSkills.setAdapter(mDevSkillAdapter);
    }

    @OnClick(R.id.activity_project_search_ibAddSkills)
    public void onClick() {
        String skill = actvNewSkill.getText().toString();
        if (skill.isEmpty()) {
            Toast.makeText(getActivity(), "Choose some skill", Toast.LENGTH_LONG).show();
        } else if (developerSkillsList.contains(skill)) {
            Toast.makeText(getActivity(), R.string.error_skill_exists, Toast.LENGTH_LONG).show();
        } else if (!allSkillsList.contains(skill)) {
            Toast.makeText(getActivity(), "No such skill in our database ", Toast.LENGTH_LONG).show();
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

}

