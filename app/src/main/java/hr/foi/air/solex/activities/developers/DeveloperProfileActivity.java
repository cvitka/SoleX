package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.core.utils.UserType;
import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.login_registration.User;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.ProjectsListAdapter;
import hr.foi.air.solex.presenters.DeveloperProfilePresenter;
import hr.foi.air.solex.presenters.DeveloperProfilePresenterImpl;

public class DeveloperProfileActivity extends DrawerActivity implements DeveloperProfileView, AdapterView.OnItemClickListener {

    private DeveloperProfilePresenter mDeveloperProfilePresenter;

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

    @BindView(R.id.activity_developer_profile_btnAddNewSkill)
    ImageButton btnAddNewSkill;

    @BindView(R.id.activity_developer_profile_btnToggleHighProjLayout)
    ImageButton btnToggleHighlightedProjects;

    @BindView(R.id.activity_developer_profile_btnToggleDevSkillsLayout)
    ImageButton btnToggleDeveloperSkills;

    @BindView(R.id.activity_developer_profile_lvHighProjects)
    ListView lvDevHighlightedProjects;

    @BindView(R.id.activity_developer_profile_lvDeveloperSkills)
    ListView lvDeveloperSkills;


    @BindView(R.id.activity_developer_profile_actvNewSkill)
    AutoCompleteTextView actvNewSkill;

    @BindView(R.id.activity_developer_profile_highProjectsLayout)
    ExpandableLayout highProjLayout;

    @BindView(R.id.activity_developer_profile_developerSkillsLayout)
    ExpandableLayout devSkillsLayout;

    @BindView(R.id.activity_developer_profile_scrollView)
    ScrollView scrollView;

    Developer mThisDeveloper;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private int developerId;
    private List<ProfileScreenProject> mProjectsList;
    private ProjectsListAdapter mProjectsAdapter;
    private List<String> allSkillsList;
    private List<String> mDevSkillList;
    private ArrayAdapter<String> mDevSkillAdapter;


    private void fixNestedScrollViews() {
        lvDeveloperSkills.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lvDevHighlightedProjects.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    public void setVisibilityForUsers(int developerId){
        if(User.isCurrentUser(UserType.DEVELOPER, developerId)){
            btnProjects.setVisibility(View.GONE);
            lastDrawerOption = R.id.company_opt_profile;
        }
        else{
            actvNewSkill.setVisibility(View.GONE);
            btnAddNewSkill.setVisibility(View.GONE);
            btnStartUpdateDeveloperData.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDeveloperProfilePresenter = new DeveloperProfilePresenterImpl(this);

        int id = 0;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id = -1;
            } else {
                id = extras.getInt("developerId");
            }
        }

        mDeveloperProfilePresenter.getDeveloperData(id);
        mDeveloperProfilePresenter.getAllSkillList();
        mDeveloperProfilePresenter.getSkillList(id);
        mDeveloperProfilePresenter.getHighlightedProjects(id);
        lastExpanded = highProjLayout;
        lastExpandedBtn = btnToggleHighlightedProjects;
        fixNestedScrollViews();
        setVisibilityForUsers(id);
    }

    private void expandableLayoutClicked(ExpandableLayout layout, ImageButton btn){
        if(layout.isExpanded()){
            layout.collapse();
            btn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.lblue_expand_img, null));
            //btn.setImageResource(R.drawable.gray_expand_img);
            lastExpanded = null;
            lastExpandedBtn = null;
        }
        else{
            if(lastExpanded != null)
                lastExpanded.collapse();
            if(lastExpandedBtn != null)
                lastExpandedBtn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.lblue_expand_img, null));

            layout.expand();
            btn.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.lblue_collapse_img, null));
            lastExpanded = layout;
            lastExpandedBtn = btn;
        }
    }

    ExpandableLayout lastExpanded;
    ImageButton lastExpandedBtn;

    @OnClick(R.id.activity_developer_profile_btnToggleHighProjLayout)
    public void btnToggleHighProjClick(){
        expandableLayoutClicked(highProjLayout, btnToggleHighlightedProjects);
    }

    @OnClick(R.id.activity_developer_profile_btnToggleDevSkillsLayout)
    public void btnToggleDevSkillsClick(){
        expandableLayoutClicked(devSkillsLayout, btnToggleDeveloperSkills);
        scrollView.fullScroll(scrollView.FOCUS_DOWN);
    }

    @OnClick(R.id.activity_developer_profile_btnAddNewSkill)
    public void btnAddNewTechClick(View view){
        String skill = actvNewSkill.getText().toString();
        if(!allSkillsList.contains(skill)){
            showToastLong(getResources().getString(R.string.error_adding_skill));
        }
        else if(mDevSkillList.contains(skill)) {
            showToastLong(getResources().getString(R.string.error_skill_exists));
        }
        else{
            mDeveloperProfilePresenter.addSkill(mThisDeveloper.getId(), skill);
            mDevSkillList.add(skill);
            mDevSkillAdapter.notifyDataSetChanged();
            actvNewSkill.setText("");
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_profile;
    }


    @OnClick(R.id.btnStartUpdateDeveloperData)
    public void btnStartUpdateDeveloperDataClick(View view){
        if(developerId ==0) {

        Intent intent = new Intent(this, UpdateDeveloperDataActivity.class);
        intent.putExtra("myObject", new Gson().toJson(mThisDeveloper));
        startActivity(intent);
        }
    }

    @OnClick(R.id.activity_developer_profile_btnProjects)
    public void btnProjectsClick(){
        if(developerId == 0) {
            //Intent intent = new Intent(this, ProjectsListingActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if(developerId !=0) {
            finish();
        }
    }

    @Override
    public void DataArrivedDeveloper(Developer developer) {
        mThisDeveloper = developer;
        txtDevelopeEmail.setText(mThisDeveloper.getEmail());
        txtDeveloperAddress.setText(mThisDeveloper.getAdresa());
        txtDevelopeName.setText(mThisDeveloper.getIme());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisDeveloper.getEmail());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProfileScreenProject clickedProject = mProjectsList.get(position);
    }

    @OnItemLongClick(R.id.activity_developer_profile_lvDeveloperSkills)
    public boolean onItemLongClick(AdapterView<?> parent, int position) {
        //we allow deletion only if profile is being viewed by its owner
        if(User.isCurrentUser(UserType.DEVELOPER, mThisDeveloper.getId())) {
            mDeveloperProfilePresenter.deleteSkill(mThisDeveloper.getId(), mDevSkillList.get(position));
            mDevSkillList.remove(position);
            mDevSkillAdapter.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    public void HighlihtedProjectsArrived(List<ProfileScreenProject> list) {
        mProjectsList = list;
        lvDevHighlightedProjects.setOnItemClickListener(this);
        mProjectsAdapter = new ProjectsListAdapter(this, R.layout.list_item_company_profile_highlighted_projects, mProjectsList, UserType.DEVELOPER);
        lvDevHighlightedProjects.setAdapter(mProjectsAdapter);

    }

    @Override
    public void allSkillsListArrived(List<String> list) {
        allSkillsList = list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, allSkillsList);
        actvNewSkill.setAdapter(adapter);
    }

    @Override
    public void developerSkillsListArrived(List<String> list) {
        mDevSkillList = list;
        mDevSkillAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDevSkillList);
        lvDeveloperSkills.setAdapter(mDevSkillAdapter);
    }
}
