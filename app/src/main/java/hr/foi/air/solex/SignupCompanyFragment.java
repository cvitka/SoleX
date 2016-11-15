package hr.foi.air.solex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.WebServiceRequest;
import com.example.webservice.models.Company;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import hr.foi.air.solex.Loaders.DataLoader;


public class SignupCompanyFragment extends Fragment {


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
        return view;
    }
    ProgressDialog progressDialog;

    public SignupCompanyFragment() {
    }

    public static SignupCompanyFragment newInstance (){
        SignupCompanyFragment signupCompanyFragment = new SignupCompanyFragment();
        return signupCompanyFragment;
    }

    @OnClick(R.id.btn_signup)
    public void signupCompany_click(View view)
    {
        String name= txtInputName.getText().toString();
        String address= txtInputAddress.getText().toString();
        String email = txtInputEmail.getText().toString();
        String password = txtInputPassword.getText().toString();
        String password2 = txtInputReEnterPassword.getText().toString();

        Company company= new Company();
        company.setName(name);
        company.setAddress(address);
        company.setEmail(email);
        company.setPassword(password);

        if(password.equals(password2) && !name.isEmpty() && !address.isEmpty() && !email.isEmpty() && !password.isEmpty() && !password2.isEmpty()) {
            DataLoader registrationLoader = new DataLoader(this);
            WebServiceRequest request = new WebServiceRequest(registrationLoader);
            request.registrationProccesCompany(name, address, email, password);
            progressDialog = new ProgressDialog(getActivity(),
                    R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.signupProgress));
            progressDialog.show();
        }
        else
        {
            Toast.makeText(getContext(), R.string.signupInfo ,Toast.LENGTH_SHORT).show();
        }
    }



    @OnClick(R.id.link_login_company)
    public void click_login() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
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

