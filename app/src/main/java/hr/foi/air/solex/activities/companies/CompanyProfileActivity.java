package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.core.utils.UserType;
import com.example.webservice.models.mcompanies.CompanyInteractorImpl;
import com.example.webservice.models.mcompanies.Company;
import com.example.webservice.models.login_registration.User;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.ProjectsListingActivity;
import hr.foi.air.solex.presenters.CompanyProfilePresenter;
import hr.foi.air.solex.presenters.CompanyProfilePresenterImplList;

public class CompanyProfileActivity extends DrawerActivity implements CompanyProfileView{
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

    Company mThisCompany;

    CompanyProfilePresenter mCompanyProfilePresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompanyProfilePresenter = new CompanyProfilePresenterImplList(this, new CompanyInteractorImpl());
        int companyId = getIntent().getExtras().getInt("companyId");
        //if company is logged in
        if(User.getInstance().getUserType() == UserType.COMPANY && companyId == User.getInstance().getId()) {
            //we hide "projects" button that should be visible only to companies
            btnProjects.setVisibility(View.GONE);
            //and we set that companyprofile was last drawer option
            lastDrawerOption = R.id.company_opt_profile;
        }
        lastExpanded = highlightedProjectsLayout;
        lastExpandedBtn = highlightProjectsBtn;

        mCompanyProfilePresenter.getCompany(companyId);
        mCompanyProfilePresenter.getHighlightedProjects(companyId);
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
        Intent intent = new Intent(this, ProjectsListingActivity.class);
        startActivity(intent);
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

    }

}
