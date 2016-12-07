package com.example.webservice.models.signup;

import android.util.Log;

import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Asus on 6.12.2016..
 */

public class DeveloperSignupModelImpl extends WebServiceCommunicator implements DeveloperSignupModel{
    private interface WebServiceInterface {
        @GET("registracijaDevelopera.php")
        Call<SignupResponse> registrirajDevelopera(@Query("ime") String name, @Query("prezime") String surName, @Query("adresa") String address, @Query("email") String email, @Query("lozinka") String password);
    }

    private SignupResponseListener mResponseListener;
    public DeveloperSignupModelImpl(SignupResponseListener listener) {
        this.mResponseListener = listener;
        initRetrofit();
    }

    @Override
    public void registerDeveloper(Developer developer, String password) {
        WebServiceInterface serviceIntf = retrofit.create(WebServiceInterface.class);
        Call<SignupResponse> call2 = serviceIntf.registrirajDevelopera(developer.getIme(), developer.getPrezime(), developer.getAdresa(), developer.getEmail(), password);
        if (call2 != null) {
            call2.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());
                            if (mResponseListener != null) {
                                mResponseListener.onRegistrationSuccessful();
                            }
                        } else {
                            if (mResponseListener != null) {
                                mResponseListener.onRegistrationFailed(response.body().getMessage());
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {
                    mResponseListener.onServerConnectionFailed();
                }
            });
        }
    }
}
