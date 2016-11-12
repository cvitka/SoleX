package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.webservice.WebServiceRequest;
import com.example.webservice.models.Developer;

import hr.foi.air.solex.Loaders.DataLoader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    TextView txtInputEmail;


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
        String password = txtInputPassword.getText().toString();
        String email = txtInputEmail.getText().toString();

        Developer developer = new Developer();
        developer.setEmail(email);
        developer.setPassword(password);
        if(!email.isEmpty() &&!password.isEmpty()) {
            DataLoader dataLoader = new DataLoader(this);
            WebServiceRequest request = new WebServiceRequest(dataLoader);
            request.loginProccess(email, password);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Your insert data is not correct!",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.link_signup)
    public void signup_click(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
