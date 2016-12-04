package hr.foi.air.solex.activities.companies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Companies.CompanyModelImpl;
import com.example.webservice.models.Companies.CompanyUpdateListener;
import com.example.webservice.models.Companies.Company;
import com.example.webservice.models.login_registration.User;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.UpdateCompanyDataPresenter;
import hr.foi.air.solex.presenters.UpdateCompanyDataPresenterImpl;

import static hr.foi.air.solex.R.id.editText3;

public class UpdateCompanyDataActivity extends DrawerActivity implements  UpdateCompanyDataView {

    Company mThisCompany;
    UpdateCompanyDataPresenter mUpdateCompanyDataPresenter;

    @BindView(R.id.btnUpdateCompanyData)
    Button btnUpdateCompanyData;

    @BindView(editText3)
    TextView txtInputNewAddress;

    @BindView(R.id.editText2)
    TextView txtInputNewEmail;

    @BindView(R.id.editText)
    TextView txtInputNewName;

    ProgressDialog progressDialog;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_company_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //dobavljamo bundlovani objekt
        String jsonMyObject = getIntent().getExtras().getString("myObject");
        mThisCompany = new Gson().fromJson(jsonMyObject, Company.class);
        setDataOnLayout(mThisCompany);

        this.mUpdateCompanyDataPresenter = new UpdateCompanyDataPresenterImpl(this, new CompanyModelImpl());
    }

    @OnClick(R.id.btnUpdateCompanyData)
    public void btnClick(View view){
        mThisCompany.setName(txtInputNewName.getText().toString());
        mThisCompany.setAddress(txtInputNewAddress.getText().toString());
        mThisCompany.setEmail(txtInputNewEmail.getText().toString());

        mUpdateCompanyDataPresenter.updateCompanyData(mThisCompany);

        progressDialog = new ProgressDialog(UpdateCompanyDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating data...");
        progressDialog.show();
    }

    @Override
    public void updateFinished() {
        progressDialog.dismiss();
        Toast.makeText(this, "Profile data has been updated", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
    }

    public void setDataOnLayout(Company company){
        mThisCompany = company;
        txtInputNewEmail.setText(mThisCompany.getEmail());
        txtInputNewAddress.setText(mThisCompany.getAddress());
        txtInputNewName.setText(mThisCompany.getName());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisCompany.getEmail());
    }
}
