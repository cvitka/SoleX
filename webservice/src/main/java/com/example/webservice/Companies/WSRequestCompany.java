package com.example.webservice.Companies;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WSRequestCompany {

    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private WSHandlerCompany listener;

    public WSRequestCompany(WSHandlerCompany listener) {
        this.listener = listener;
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void getCompanyData(int id)
    {
        WSInterfaceCompany interfaceCompany = retrofit.create(WSInterfaceCompany.class);
        Call<WSResponseCompany> call = interfaceCompany.dohvatiKompaniju(id);
        if (call!=null){
            call.enqueue(new Callback<WSResponseCompany>() {
                @Override
                public void onResponse(Call<WSResponseCompany> call, Response<WSResponseCompany> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if (listener!=null){
                                listener.onDataCome(response.body().getId(),response.body().getNaziv(),response.body().getAdresa(),response.body().getEmail(),response.body().getSlika());
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
