package com.example.webservice.models.login_registration;


import android.util.Log;

import com.example.webservice.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cvitka on 11.11.16..
 */

public class LoginModelImpl extends WebServiceCommunicator implements LoginModel{
    private interface WebServiceInterface {
        @GET("prijava.php")
        Call<LoginResponse> checkPrijava(@Query("email") String email, @Query("lozinka") String password);
    }

    private LoginResponseListener presenter;
    private String mEmail;

    public LoginModelImpl(LoginResponseListener listener) {
        this.presenter = listener;
        initRetrofit();
    }

    @Override
    public void checkLogin(String email, String password) {
        mEmail = email;
        WebServiceInterface serviceInterface = retrofit.create(WebServiceInterface.class);
        Call<LoginResponse> call = serviceInterface.checkPrijava(email, password);
        if (call != null) {
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {

                        if (response.body().getSuccess().equals("1")) {
                            if (presenter != null) {
                                presenter.onLoginSuccessful(response.body().getId(), mEmail, response.body().getType());
                            }
                        } else {
                            if (presenter != null) {
                                presenter.onLoginFailed(response.body().getMessage());
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                    presenter.onServerConnectionFailed();
                }
            });
        }

    }
}
