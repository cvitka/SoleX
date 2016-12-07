package com.example.webservice.models;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Asus on 6.12.2016..
 */

public abstract class WebServiceCommunicator {
    protected Retrofit retrofit;
    protected final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";

    protected void initRetrofit(){
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
