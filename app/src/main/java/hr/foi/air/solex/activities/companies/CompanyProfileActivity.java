package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.utils.UserType;
import com.example.webservice.Companies.WSRequestCompany;
import com.example.webservice.models.Company;
import com.example.webservice.models.User;

import butterknife.BindView;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.ProjectsListingActivity;
import hr.foi.air.solex.activities.Listeners.CompanyDataListener;
import hr.foi.air.solex.loaders.DLCompany;

public class CompanyProfileActivity extends DrawerActivity implements CompanyDataListener{

    @BindView(R.id.imgBtnTestExpand)
    ImageButton highlightProjectsBtn;

    @BindView(R.id.highlightedProjectsLayout)
    RelativeLayout highlightedProjectsLayout;

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

    Company mThisCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if company is logged in
        if(User.getInstance().getUserType() == UserType.COMPANY) {
            //we hide "projects" button that should be visible only to companies
            btnProjects.setVisibility(View.GONE);
            //and we set that companyprofile was last drawer option
            lastDrawerOption = R.id.company_opt_profile;
            DLCompany loader = new DLCompany(this);
            WSRequestCompany request = new WSRequestCompany(loader);
            request.getCompanyData(User.getInstance().getId());
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_profile;
    }

    @OnClick(R.id.btnStartUpdateCompanyData)
    public void btnUpdateCompanyDataClick(View view){
        Intent intent = new Intent(this, UpdateCompanyDataActivity.class);
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
    }
}
