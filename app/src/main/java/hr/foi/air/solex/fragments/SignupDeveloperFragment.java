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

import com.example.webservice.models.login_registration.WebServiceRequest;
import com.example.webservice.models.Developers.Developer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.activities.common.LoginActivity;
import hr.foi.air.solex.R;
import hr.foi.air.solex.loaders.DataLoader;


public class SignupDeveloperFragment extends Fragment {

    @BindView(R.id.input_name)
    TextView txtInputName;

    @BindView(R.id.input_surname)
    TextView txtInputSurname;

    @BindView(R.id.input_address)
    TextView txtInputAddress;

    @BindView(R.id.input_email)
    TextView txtInputEmail;

    @BindView(R.id.input_password)
    TextView txtInputPassword;

    @BindView(R.id.input_reEnterPassword)
    TextView txtInputReEnterPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_signup_developer, container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public SignupDeveloperFragment() {
    }

    public static SignupDeveloperFragment newInstance(){
        SignupDeveloperFragment signupDeveloperFragment = new SignupDeveloperFragment();
        return signupDeveloperFragment;
    }

    @OnClick(R.id.btn_signup)
    public void signup_click(View view)
    {
        String name= txtInputName.getText().toString();
        String surName= txtInputSurname.getText().toString();
        String address= txtInputAddress.getText().toString();
        String email = txtInputEmail.getText().toString();
        String password = txtInputPassword.getText().toString();
        String password2 = txtInputReEnterPassword.getText().toString();
        Developer developer= new Developer();
        developer.setIme(name);
        developer.setPrezime(surName);
        developer.setAdresa(address);
        developer.setEmail(email);
        developer.setPassword(password);

        if(password.equals(password2) && !name.isEmpty()&& !surName.isEmpty() && !address.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            DataLoader registrationLoader = new DataLoader(this);
            WebServiceRequest request = new WebServiceRequest(registrationLoader);
            request.registrationProcces(name, surName, address, email, password);
            progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.signupProgress));
            progressDialog.show();
        }
        else
        {
            Toast.makeText(getContext(), R.string.signupInfo,Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.link_login_developer)
    public void click_login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void registration_failed(final String message, final boolean status){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (status == false) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
            }
        }, 1000);
    }
}
