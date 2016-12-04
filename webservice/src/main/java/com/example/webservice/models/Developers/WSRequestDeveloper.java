package com.example.webservice.models.Developers;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WSRequestDeveloper {

    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private WSHandlerDeveloper listener;
    private WSListHandlerDevelopers listListener;

    private void InitRetrofit(){
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public WSRequestDeveloper(WSHandlerDeveloper listener) {
        this.listener = listener;
        InitRetrofit();
    }

    public WSRequestDeveloper(WSListHandlerDevelopers wsListHandler){
        this.listListener = wsListHandler;
        InitRetrofit();
    }

    public void getDeveloperListData(int [] id){//neki ulazni parametar koji već nam bude trebao, možemo imat više razl. metoda za dohvat liste po različitim parametrima

    }

    public void getDeveloperData(int id){
        WSInterfaceDeveloper interfaceCompany = retrofit.create(WSInterfaceDeveloper.class);
        Call<WSResponseDeveloper> call = interfaceCompany.dohvatiDevelopere(id);
        if (call!=null)
        {
            call.enqueue(new Callback<WSResponseDeveloper>() {
                @Override
                public void onResponse(Call<WSResponseDeveloper> call, Response<WSResponseDeveloper> response) {
                    if(response.isSuccessful())
                    {
                        if (listener != null)
                        {
                            listener.onDataComeDeveloper(response.body().getId(),response.body().getIme(),response.body().getPrezime(),response.body().getAdresa(),response.body().getEmail(),response.body().getKontaktBroj(),response.body().getGodineIskustva(),response.body().getSlika());
                            //response.body().
                        }
                    }
                }
                @Override
                public void onFailure(Call<WSResponseDeveloper> call, Throwable t) {
                }
            });
        }
    }



}
