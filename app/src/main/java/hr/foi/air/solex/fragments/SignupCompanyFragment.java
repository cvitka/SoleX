package hr.foi.air.solex.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Companies.Company;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.LoginActivity;
import hr.foi.air.solex.presenters.CompanySignupPresenter;
import hr.foi.air.solex.presenters.CompanySignupPresenterImpl;


public class SignupCompanyFragment extends Fragment implements SignupView{
    @BindView(R.id.input_name)
    TextView txtInputName;

    @BindView(R.id.input_address)
    TextView txtInputAddress;

    @BindView(R.id.input_email)
    TextView txtInputEmail;

    @BindView(R.id.input_password)
    TextView txtInputPassword;

    @BindView(R.id.input_reEnterPassword)
    TextView txtInputReEnterPassword;

    CompanySignupPresenter mCompanySignupPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_signup_company, container, false);
        ButterKnife.bind(this,view);
        mCompanySignupPresenter = new CompanySignupPresenterImpl(this);
        return view;
    }
    ProgressDialog progressDialog;

    public static SignupCompanyFragment newInstance (){
        return new SignupCompanyFragment();
    }

    @OnClick(R.id.btn_signup)
    public void signupCompany_click(View view){
        if(txtInputName.getText().toString().isEmpty()
        || txtInputAddress.getText().toString().isEmpty()
        || txtInputEmail.getText().toString().isEmpty()
        || txtInputPassword.getText().toString().isEmpty()
        || txtInputReEnterPassword.getText().toString().isEmpty()){
            showToast("All fields must be filled");
        }
        else if(!txtInputPassword.getText().toString().equals(txtInputReEnterPassword.getText().toString())){
            showToast("Passwords don't match!");
        }
        else{
            Company company = new Company();
            company.setName(txtInputName.getText().toString());
            company.setAddress(txtInputAddress.getText().toString());
            company.setEmail(txtInputEmail.getText().toString());
            //
            mCompanySignupPresenter.tryRegister(company, txtInputPassword.getText().toString());
            progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.signupProgress));
            progressDialog.show();
        }
    }

    @OnClick(R.id.link_login_company)
    public void click_login() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void signupSuccessful() {
        showToast("Registration successful! Redirecting on login screen...");
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }

        }, 1000);
    }

    @Override
    public void signupFailed(String message) {
        showToast(message);
    }
}

