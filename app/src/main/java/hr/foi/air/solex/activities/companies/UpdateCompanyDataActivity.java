package hr.foi.air.solex.activities.companies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.webservice.WSRequestUpdateCompany;
import com.example.webservice.models.Company;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.loaders.DLCompany;

public class UpdateCompanyDataActivity extends DrawerActivity {

    @BindView(R.id.btnUpdateCompanyData)
    Button btnUpdateCompanyData;

    @BindView(R.id.editText3)
    TextView txtInputNewAddress;

    @BindView(R.id.editText2)
    TextView txtInputNewEmail;

    @BindView(R.id.editText)
    TextView txtInputNewName;

    ProgressDialog progressDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_company_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnUpdateCompanyData)
    public void btnClick(View view){

        String name= txtInputNewName.getText().toString();
        String address= txtInputNewAddress.getText().toString();
        String email = txtInputNewEmail.getText().toString();

        int id = Company.getInstance().getId();

        Company company= new Company();
        company.setName(name);
        company.setAddress(address);
        company.setEmail(email);
        company.setId(id);

        DLCompany loader = new DLCompany(this);
        final WSRequestUpdateCompany request = new WSRequestUpdateCompany(loader);
        request.companyUpdateProcces(id,name,address,email);

        progressDialog = new ProgressDialog(UpdateCompanyDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating data...");
        progressDialog.show();
    }

}
