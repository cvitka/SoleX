package hr.foi.air.solex.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Developers.Developer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.activities.common.LoginActivity;
import hr.foi.air.solex.R;
import hr.foi.air.solex.presenters.DeveloperSignupPresenter;
import hr.foi.air.solex.presenters.DeveloperSignupPresenterImpl;


public class SignupDeveloperFragment extends Fragment implements SignupView{

    @BindView(R.id.fragment_signup_developer_etInputName)
    TextView txtInputName;

    @BindView(R.id.fragment_signup_developer_etInputSurname)
    TextView txtInputSurname;

    @BindView(R.id.fragment_signup_developer_etInputAddress)
    TextView txtInputAddress;

    @BindView(R.id.fragment_signup_developer_etInputEmail)
    TextView txtInputEmail;

    @BindView(R.id.fragment_signup_developer_etInputPassword)
    TextView txtInputPassword;

    @BindView(R.id.fragment_signup_developer_etReEnterPassword)
    TextView txtInputReEnterPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ProgressDialog progressDialog;
    DeveloperSignupPresenter mDeveloperSignupPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_signup_developer, container,false);
        ButterKnife.bind(this,view);
        mDeveloperSignupPresenter = new DeveloperSignupPresenterImpl(this);
        return view;
    }

    public SignupDeveloperFragment() {
    }

    public static SignupDeveloperFragment newInstance(){
        SignupDeveloperFragment signupDeveloperFragment = new SignupDeveloperFragment();
        return signupDeveloperFragment;
    }

    @OnClick(R.id.fragment_signup_developer_btnSignup)
    public void signup_click(View view)
    {
        if(txtInputName.getText().toString().isEmpty()
        || txtInputSurname.getText().toString().isEmpty()
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
            Developer developer= new Developer();
            developer.setIme(txtInputName.getText().toString());
            developer.setPrezime(txtInputSurname.getText().toString());
            developer.setAdresa(txtInputAddress.getText().toString());
            developer.setEmail(txtInputEmail.getText().toString());
            //
            mDeveloperSignupPresenter.tryRegister(developer, txtInputPassword.getText().toString());
            progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.signupProgress));
            progressDialog.show();
        }
    }

    @OnClick(R.id.fragment_signup_developer_tvLinkLoginDeveloper)
    public void click_login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
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
