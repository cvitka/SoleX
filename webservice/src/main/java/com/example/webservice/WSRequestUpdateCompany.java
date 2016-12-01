package com.example.webservice;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomislav on 11/30/16.
 */

public class WSRequestUpdateCompany {

    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private WSHandlerCompany listener;

    public WSRequestUpdateCompany(WSHandlerCompany listener) {
        this.listener = listener;
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void companyUpdateProcces(int id, String name, String address, String email){
        WSInterfaceCompany servInterface = retrofit.create(WSInterfaceCompany.class);
        Call<WSResponseCompany> call = servInterface.azurirajKompaniju(id,name,address,email);
        if (call != null) {
            call.enqueue(new Callback<WSResponseCompany>() {
                @Override
                public void onResponse(Call<WSResponseCompany> call, Response<WSResponseCompany> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if(listener!=null){
                                listener.onCompanyUpdate();
                            }


                        }

                    }
                }

                @Override
                public void onFailure(Call<WSResponseCompany> call, Throwable t) {

                }
            });
        }

    }

}
