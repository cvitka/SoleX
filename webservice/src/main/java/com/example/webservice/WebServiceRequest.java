package com.example.webservice;


import android.util.Log;

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
                            User user = new User();
                            user.setId(response.body().getId());
                            user.setEmail(email);
                            user.setType(response.body().getType());
                            if(listener !=null) {
                                listener.onLogin(user);
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
