package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.input_password)
    TextView txtInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void login_click(View view){
        String input = txtInputPassword.getText().toString();
        if(input.equals("firma")) {
            Intent intent = new Intent(this, CompanyProfileActivity.class);
            startActivity(intent);
        }
        else if(input.equals("devel")) {
            Intent intent = new Intent(this, DeveloperProfileActivity.class);
            startActivity(intent);
        }

    }

    @OnClick(R.id.link_signup)
    public void signup_click(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
