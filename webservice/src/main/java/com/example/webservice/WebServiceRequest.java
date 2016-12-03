package com.example.webservice;


import android.util.Log;

import com.example.core.utils.UserType;
import com.example.webservice.models.Company;
import com.example.webservice.models.Developer;
import com.example.core.utils.LoginStatus;
import com.example.webservice.models.User;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cvitka on 11.11.16..
 */

public class WebServiceRequest {
    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private WebServiceHandler listener;

    public WebServiceRequest(WebServiceHandler listener) {
        this.listener = listener;
        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    LoginStatus loginStatus = new LoginStatus();

    public void loginProccess(final String email, String password) {
        WebServiceInterface serviceInterface = retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call = serviceInterface.checkPrijava(email, password);
        final LoginStatus loginStatus = new LoginStatus();
        if (call != null) {
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if (response.isSuccessful()) {

                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());
                            User.getInstance().setId(response.body().getId());
                            Company.getInstance().setId(response.body().getId());
                            Developer.getInstance().setId(response.body().getId());
                            User.getInstance().setEmail(email);
                            if (response.body().getType().equals(UserType.COMPANY.stringVal())) {
                                User.getInstance().setUserType(UserType.COMPANY);
                                Company.getInstance().setId(response.body().getId());

                            } else if (response.body().getType().equals(UserType.DEVELOPER.stringVal())) {
                                User.getInstance().setUserType(UserType.DEVELOPER);
                                Developer.getInstance().setId(response.body().getId());
                            }
                            if (listener != null) {
                                listener.onLogin();
                            }
                        } else {
                            if (listener != null) {
                                loginStatus.setMessage(response.body().getMessage());
                                loginStatus.setSuccess(false);
                                listener.onFailedLogin(loginStatus);
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                    loginStatus.setMessage(t.getMessage());
                    loginStatus.setSuccess(false);
                    listener.onFailedRegistration(loginStatus);
                }
            });
        }

    }

    public void registrationProcces(final String name, String surName, String address, final String email, String password) {
        WebServiceInterface serviceIntf = retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call2 = serviceIntf.registrirajDevelopera(name, surName, address, email, password);
        if (call2 != null) {
            call2.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());

                            if (listener != null) {

                                listener.onRegistration();
                            }
                        } else {
                            if (listener != null) {
                                loginStatus.setMessage(response.body().getMessage());
                                loginStatus.setSuccess(false);
                                listener.onFailedRegistration(loginStatus);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                    loginStatus.setMessage(t.getMessage());
                    loginStatus.setSuccess(false);
                    listener.onFailedRegistration(loginStatus);
                }
            });
        }

    }

    public void registrationProccesCompany(final String name, String address, final String email, String password) {
        WebServiceInterface serviceIntf = retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call3 = serviceIntf.registrirajPoduzece(name, address, email, password);
        if (call3 != null) {
            call3.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());
                            Company.getInstance().setEmail(email);
                            if (listener != null) {
                                listener.onRegistrationCompany();
                            }
                        } else {
                            if (listener != null) {
                                loginStatus.setMessage(response.body().getMessage());
                                loginStatus.setSuccess(false);
                                listener.onFailedRegistrationCompany(loginStatus);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                    loginStatus.setMessage(t.getMessage());
                    loginStatus.setSuccess(false);
                    listener.onFailedRegistrationCompany(loginStatus);

                }
            });
        }
    }

}
