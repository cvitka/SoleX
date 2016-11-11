package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.models.Developers;
import hr.foi.air.solex.webservice.WebService;
import hr.foi.air.solex.webservice.WebServiceRequest;
import hr.foi.air.solex.webservice.WebServiceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_email)
    TextView txtInputEmail;


    @BindView(R.id.input_password)
    TextView txtInputPassword;

    public static String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private Callback<WebServiceResponse> failed;
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
        loginProcess(email,password);
    }
    public void loginProcess(String email, String password){

        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        WebService webServiceInterface = retrofit.create(WebService.class);

        Developers developer = new Developers();
        developer.setEmail(email);
        developer.setPassword(password);

        WebServiceRequest request = new WebServiceRequest();
        request.setDeveloper(developer);

        Call<WebServiceResponse> call = webServiceInterface.checkPrijava(email,password);
        call.enqueue(new Callback<WebServiceResponse>() {
            @Override
            public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                if(response.isSuccessful()){
                    Log.d("Api","Uspjeli");
                    if(response.body().getSuccess().equals("1") && response.body().getType().equals("developer")){
                        Intent intent = new Intent(LoginActivity.this, DeveloperProfileActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(LoginActivity.this, CompanyProfileActivity.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                Log.d("Api","failiure");
            }
        });
    }

    @OnClick(R.id.link_signup)
    public void signup_click(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
