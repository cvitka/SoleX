package hr.foi.air.solex.activities.companies;

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

import hr.foi.air.solex.activities.common.ProjectDisplayActivity;
import hr.foi.air.solex.activities.common.ProjectListingActivity;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.mcompanies.CompanyInteractorImpl;
import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.ProjectsListAdapter;
import hr.foi.air.solex.presenters.companies.CompanyProfilePresenter;
import hr.foi.air.solex.presenters.companies.CompanyProfilePresenterImpl;

public class CompanyProfileActivity extends DrawerActivity implements CompanyProfileView, AdapterView.OnItemClickListener{
    @BindView(R.id.activity_company_profile_btnToggleProjectsLayout)
    ImageButton highlightProjectsBtn;

    @BindView(R.id.activity_company_profile_projectsLayout)
    ExpandableLayout highlightedProjectsLayout;

    @BindView(R.id.activity_company_profile_btnToggleGenInfoLayout)
    ImageButton generalInfoBtn;

    @BindView(R.id.activity_company_profile_generalInfoLayout)
    ExpandableLayout generalInfoLayout;

    @BindView(R.id.activity_company_profile_btnToggleMainTechLayout)
    ImageButton mainTechBtn;

    @BindView(R.id.activity_company_profile_mainTechLayout)
    ExpandableLayout mainTechLayout;

    @BindView(R.id.btnStartUpdateCompanyData)
    Button btnStartUpdateCompanyData;

    @BindView(R.id.activity_company_profile_btnProjects)
    Button btnProjects;

    @BindView(R.id.textView3)
    TextView txtCompanyAddress;

    @BindView(R.id.textView2)
    TextView txtCompanyEmail;

    @BindView(R.id.textView)
    TextView txtCompanyName;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    //@BindView(R.id.activity_company_profile_tvCompanyDescription)
    //TextView txtCompanyDescription;


    @BindView(R.id.activity_company_profile_tvDirector)
    TextView txtDirector;

    @BindView(R.id.activity_company_profile_tvNumberEmployees)
    TextView txtNumberEployees;

    @BindView(R.id.activity_company_profile_tvWebPage)
    TextView txtWebPage;

    @BindView(R.id.activity_company_profile_lvHighProjects)
    ListView lvHighProjects;


    @BindView(R.id.activity_company_profile_lvMainTech)
    ListView lvMainTech;


    @BindView(R.id.activity_company_profile_actvNewTech)
    AutoCompleteTextView actvNewTech;


    @BindView(R.id.activity_company_profile_btnAddNewTech)
    ImageButton btnAddNewTech;

    @BindView(R.id.activity_company_profile_scrollView)
    ScrollView scrollView;



    Company mThisCompany;

    CompanyProfilePresenter mCompanyProfilePresenter;

    List<ProfileScreenProject> mProjectsList;
    List<String> mMainTechList;
    ArrayAdapter<String> mMainTechAdapter;
    List<String> allTechList;
    private ProjectsListAdapter mProjectsAdapter;


    public void setVisibilityForUsers(int companyId){
        if(User.isCurrentUser(UserType.COMPANY, companyId)){
            btnProjects.setVisibility(View.GONE);
            lastDrawerOption = R.id.company_opt_profile;
        }
        else{
            actvNewTech.setVisibility(View.GONE);
            btnAddNewTech.setVisibility(View.GONE);
            btnStartUpdateCompanyData.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompanyProfilePresenter = new CompanyProfilePresenterImpl(this, new CompanyInteractorImpl());
        int companyId = getIntent().getExtras().getInt("companyId");
        //if company is logged in
        setVisibilityForUsers(companyId);


        mCompanyProfilePresenter.getCompany(companyId);
        mCompanyProfilePresenter.getHighlightedProjects(companyId);
        //autocomplete list is not needed when user looking at profile is not the owner
        if(User.isCurrentUser(UserType.COMPANY, companyId))
            mCompanyProfilePresenter.getAllSkillList();
        mCompanyProfilePresenter.getSkillList(companyId);
        fixNestedScrollViews();

        lastExpanded = highlightedProjectsLayout;
        lastExpandedBtn = highlightProjectsBtn;
    }

    private void fixNestedScrollViews() {
        lvMainTech.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        lvHighProjects.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ProfileScreenProject clickedProject = mProjectsList.get(position);
        //ako smo vlasnik profila
        if(User.isCurrentUser(UserType.COMPANY, mThisCompany.getId())) {
            Intent intent = new Intent(this, ProjectManagementActivity.class);//otvaramo project management
            intent.putExtra("isOwner", User.isCurrentUser(UserType.COMPANY, mThisCompany.getId()));
            intent.putExtra("projectId", clickedProject.getId());
            startActivity(intent);
        }
        else {//inače otvaramo project display
            Intent intent = new Intent(this, ProjectDisplayActivity.class);
            intent.putExtra("projectId", clickedProject.getId());
            startActivity(intent);
        }
    }

    @OnItemLongClick(R.id.activity_company_profile_lvMainTech)
    public boolean onItemLongClick(AdapterView<?> parent, int position) {
        //we allow deletion only if profile is being viewed by its owner
        if(User.isCurrentUser(UserType.COMPANY, mThisCompany.getId())) {
            mCompanyProfilePresenter.deleteSkill(mThisCompany.getId(), mMainTechList.get(position));
            mMainTechList.remove(position);
            mMainTechAdapter.notifyDataSetChanged();
        }
        return true;
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

    @OnClick(R.id.activity_company_profile_btnToggleProjectsLayout)
    public void btnToggleHighlightedProjects(View view){
        expandableLayoutClicked(highlightedProjectsLayout, highlightProjectsBtn);


    }

    @OnClick(R.id.activity_company_profile_btnToggleGenInfoLayout)
    public void btnToggleGeneralInfo(View view){
        expandableLayoutClicked(generalInfoLayout, generalInfoBtn);
    }

    @OnClick(R.id.activity_company_profile_btnToggleMainTechLayout)
    public void btnToggleMainTech(View view){
        expandableLayoutClicked(mainTechLayout, mainTechBtn);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_profile;
    }

    @OnClick(R.id.btnStartUpdateCompanyData)
    public void btnUpdateCompanyDataClick(View view){
        Intent intent = new Intent(this, UpdateCompanyDataActivity.class);
        intent.putExtra("myObject", new Gson().toJson(mThisCompany));
        startActivity(intent);
    }

    @OnClick(R.id.activity_company_profile_btnProjects)
    public void btnClick(View view){
        Intent intent = new Intent(this, ProjectListingActivity.class);

        intent.putExtra("ownerId", mThisCompany.getId());
        intent.putExtra("ownerName", mThisCompany.getName());
        intent.putExtra("type", UserType.COMPANY.intVal());
        startActivity(intent);
    }

    @OnClick(R.id.activity_company_profile_btnAddNewTech)
    public void btnAddNewTechClick(View view){
        String skill = actvNewTech.getText().toString();
        if(!allTechList.contains(skill)){
            showToastLong(getResources().getString(R.string.error_adding_skill));
        }
        else if(mMainTechList.contains(skill)) {
            showToastLong(getResources().getString(R.string.error_skill_exists));
        }
        else{
            mCompanyProfilePresenter.addSkill(mThisCompany.getId(), skill);
            mMainTechList.add(skill);
            mMainTechAdapter.notifyDataSetChanged();
            actvNewTech.setText("");
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void DataArrived(Company company) {

        mThisCompany = company;
        txtCompanyEmail.setText(mThisCompany.getEmail());
        txtCompanyAddress.setText(mThisCompany.getAddress());
        txtCompanyName.setText(mThisCompany.getName());
        //txtCompanyDescription.setText(mThisCompany.getOpisPoduzeca());
        txtDirector.setText(mThisCompany.getDirektor());
        txtNumberEployees.setText(Integer.toString(mThisCompany.getBrojZaposlenika()));
        txtWebPage.setText(mThisCompany.getWebStranica());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisCompany.getEmail());
    }

    @Override
    public void HighlihtedProjectsArrived(List<ProfileScreenProject> list) {
        mProjectsList = list;
        lvHighProjects.setOnItemClickListener(this);
        mProjectsAdapter = new ProjectsListAdapter(this, R.layout.list_item_company_profile_highlighted_projects, mProjectsList, UserType.COMPANY);
        lvHighProjects.setAdapter(mProjectsAdapter);
    }

    @Override
    public void allSkillsListArrived(List<String> list) {
        allTechList = list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, allTechList);

        actvNewTech.setAdapter(adapter);

    }

    @Override
    public void companySkillsListArrived(List<String> list) {
        mMainTechList = list;
        mMainTechAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mMainTechList);
        lvMainTech.setAdapter(mMainTechAdapter);

    }
}
