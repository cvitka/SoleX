package hr.foi.air.solex.activities.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Developers.Developer;

import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.presenters.LoginPresenter;
import hr.foi.air.solex.presenters.LoginPresenterImpl;


public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter mLoginPresenter;

    @BindView(R.id.input_email)
    TextView txtInputEmail;

    @BindView(R.id.input_password)
    TextView txtInputPassword;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_login)
    public void login_click(View view) {
        if(txtInputEmail.getText().toString().isEmpty() || txtInputPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill in all data", Toast.LENGTH_LONG).show();
        }
        else{
            mLoginPresenter.tryLogin(txtInputEmail.getText().toString(), txtInputPassword.getText().toString());
            progressDialog = new ProgressDialog(this,
                    R.style.AppTheme_Bright_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.loginProgress));
            progressDialog.show();
        }
    }

    @Override
    public void onDeveloperLoginSuccess() {
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCompanyLoginSuccess() {
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFailed(final String message) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

            }
        }, 1000);
    }

    @OnClick(R.id.link_signup)
    public void signup_click(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
