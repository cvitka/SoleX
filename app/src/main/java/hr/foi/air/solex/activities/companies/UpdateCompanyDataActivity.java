package hr.foi.air.solex.activities.companies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.Companies.WSRequestCompany;
import com.example.webservice.Companies.WSUpdateCompany;
import com.example.webservice.Companies.WSUpdateCompanyListener;
import com.example.webservice.models.Company;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.Listeners.CompanyDataListener;
import hr.foi.air.solex.loaders.DLCompany;

import static hr.foi.air.solex.R.id.editText3;

public class UpdateCompanyDataActivity extends DrawerActivity implements CompanyDataListener, WSUpdateCompanyListener{

    Company mThisCompany;

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

        int id = Company.getInstance().getId();
        DLCompany loader = new DLCompany(this);
        WSRequestCompany request = new WSRequestCompany(loader);
        request.getCompanyData(id);

    }



    @OnClick(R.id.btnUpdateCompanyData)
    public void btnClick(View view){

        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);

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
        final WSUpdateCompany request = new WSUpdateCompany(this);
        request.companyUpdateProcces(id,name,address,email);



        progressDialog = new ProgressDialog(UpdateCompanyDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating data...");
        progressDialog.show();



    }
/*
    public void getList(){

        polje [50] CompanyId;
        for(int i=0; i<50; i++){
            DLCompany loader = new DLCompany(this);
            WSRequestCompany request = new WSRequestCompany(loader);
            request.getCompanyData(polje[i]);
        }
    }
*/
    @Override
    public void onCompanyUpdate() {
        //pozvat natrag aktivnost profila
        progressDialog.dismiss();
        Toast.makeText(this, "Profile data has been updated", Toast.LENGTH_LONG).show();



    }

    public void DataArrived(Company company){
        mThisCompany = company;
        txtInputNewEmail.setText(mThisCompany.getEmail());
        txtInputNewAddress.setText(mThisCompany.getAddress());
        txtInputNewName.setText(mThisCompany.getName());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisCompany.getEmail());
    }


}
