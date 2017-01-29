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

import com.google.firebase.iid.FirebaseInstanceId;

import hr.foi.air.solex.models.mcompanies.Company;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.LoginActivity;
import hr.foi.air.solex.presenters.common.CompanySignupPresenter;
import hr.foi.air.solex.presenters.common.CompanySignupPresenterImpl;


public class SignupCompanyFragment extends Fragment implements SignupView{
    @BindView(R.id.fragment_signup_company_etInputName)
    TextView txtInputName;

    @BindView(R.id.fragment_signup_company_etInputAddress)
    TextView txtInputAddress;

    @BindView(R.id.fragment_signup_company_etInputEmail)
    TextView txtInputEmail;

    @BindView(R.id.fragment_signup_company_etInputPassword)
    TextView txtInputPassword;

    @BindView(R.id.fragment_signup_company_etReEnterPassword)
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

    @OnClick(R.id.fragment_signup_company_btnSignup)
    public void signupCompany_click(View view){
        if(txtInputName.getText().toString().isEmpty()
        || txtInputAddress.getText().toString().isEmpty()
        || txtInputEmail.getText().toString().isEmpty()
        || txtInputPassword.getText().toString().isEmpty()
        || txtInputReEnterPassword.getText().toString().isEmpty()){
            showToast(getString(R.string.all_fields));
        }
        else if(!txtInputPassword.getText().toString().equals(txtInputReEnterPassword.getText().toString())){
            showToast(getString(R.string.password_dont_match));
        }
        else{
            Company company = new Company();
            company.setName(txtInputName.getText().toString());
            company.setAddress(txtInputAddress.getText().toString());
            company.setEmail(txtInputEmail.getText().toString());
            company.setToken(FirebaseInstanceId.getInstance().getToken());
            //
            mCompanySignupPresenter.tryRegister(company, txtInputPassword.getText().toString());
            progressDialog = new ProgressDialog(getActivity(),
                        R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.signupProgress));
            progressDialog.show();
        }
    }

    @OnClick(R.id.fragment_signup_company_tvLinkLoginCompany)
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
        showToast(getString(R.string.registration_success));
        progressDialog.dismiss();
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
        Toast.makeText(getActivity(),R.string.server_error,Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }
}

