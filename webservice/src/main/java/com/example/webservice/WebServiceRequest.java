package com.example.webservice;


import android.util.Log;

import com.example.core.utils.UserType;
import com.example.webservice.models.Company;
import com.example.webservice.models.Developer;
import com.example.webservice.models.User;
import com.google.gson.Gson;

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

    public WebServiceRequest(WebServiceHandler listener){
        this.listener = listener;
        OkHttpClient client = new OkHttpClient();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void loginProccess(final String email, String password){
        WebServiceInterface serviceInterface = retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call = serviceInterface.checkPrijava(email,password);
        if(call !=null){
            call.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if(response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            Log.d("API",response.body().getSuccess());
                            User.getInstance().setId(response.body().getId());
                            User.getInstance().setEmail(email);
                            if(response.body().getType().equals(UserType.COMPANY.stringVal())){
                                User.getInstance().setUserType(UserType.COMPANY);
                            }
                            else if(response.body().getType().equals(UserType.DEVELOPER.stringVal())){
                                User.getInstance().setUserType(UserType.DEVELOPER);
                            }
                            if(listener !=null) {
                                listener.onLogin();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                }
            });
        }

    }

    public void registrationProcces(final String name, String surName, String address, final String email, String password)
    {
        WebServiceInterface serviceIntf= retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call2 = serviceIntf.registrirajDevelopera(name,surName,address,email,password);
        if (call2 != null)
        {
            call2.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body().getSuccess().equals("1"))
                        {
                            Log.d("API",response.body().getSuccess());
                            Developer.getInstance().setEmail(email);
                            if(listener !=null) {

                                listener.onRegistration();
                            }


                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());

                }
            });
        }

    }

    public void registrationProccesCompany(final String name, String address, final String email, String password)
    {
        WebServiceInterface serviceIntf= retrofit.create(WebServiceInterface.class);
        Call<WebServiceResponse> call3 = serviceIntf.registrirajPoduzece(name,address,email,password);
        if(call3!= null)
        {
            call3.enqueue(new Callback<WebServiceResponse>() {
                @Override
                public void onResponse(Call<WebServiceResponse> call, Response<WebServiceResponse> response) {
                    if(response.isSuccessful())
                    {
                        if(response.body().getSuccess().equals("1"))
                        {
                            Log.d("API",response.body().getSuccess());
                            Company.getInstance().setEmail(email);
                            if(listener!=null)
                            {
                                listener.onRegistrationCompany();

                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WebServiceResponse> call, Throwable t) {
                    Log.d("Api", t.getMessage());

                }
            });
        }
    }

}
