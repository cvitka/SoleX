package hr.foi.air.solex.activities.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import hr.foi.air.solex.models.login_registration.User;

import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.presenters.common.LoginPresenter;
import hr.foi.air.solex.presenters.common.LoginPresenterImpl;
import hr.foi.air.solex.utils.Utility;


public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter mLoginPresenter;

    @BindView(R.id.activity_login_etInputEmail)
    TextView txtInputEmail;

    @BindView(R.id.activity_login_etInputPassword)
    TextView txtInputPassword;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        (new Utility()).setLanguage(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.activity_login_btnLogin)
    public void login_click(View view) {
        if (txtInputEmail.getText().toString().isEmpty() || txtInputPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.fill_data, Toast.LENGTH_LONG).show();
        } else {
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
        intent.putExtra("developerId", User.getInstance().getId());
        intent.putExtra("hhh", true);
        progressDialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void onCompanyLoginSuccess() {
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("companyId", User.getInstance().getId());
        progressDialog.dismiss();
        startActivity(intent);
    }

    @Override
    public void onLoginFailed() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), R.string.login_wrong_cred, Toast.LENGTH_LONG).show();

            }
        }, 1000);
    }

    @Override
    public void onContactServerFailed() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_LONG).show();

            }
        }, 1000);
    }

    @OnClick(R.id.activity_login_tvLink_signup)
    public void signup_click(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }


    private Boolean exit = false;

    @Override
    public void onBackPressed() {

        if (exit) {
            moveTaskToBack(true);
            finish();
            System.runFinalizersOnExit(true);
            System.exit(0);
        } else {
            Toast.makeText(this, R.string.back_to_exit,
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
