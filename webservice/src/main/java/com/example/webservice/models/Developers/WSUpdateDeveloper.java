package com.example.webservice.models.Developers;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WSUpdateDeveloper {

    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private WSUpdateDeveloperListener listener;

    public WSUpdateDeveloper(WSUpdateDeveloperListener listener) {
        this.listener = listener;
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void developerUpdateProcces(int id, String name, String address, String email){
        WSInterfaceDeveloper servInterface = retrofit.create(WSInterfaceDeveloper.class);
        Call<WSResponseDeveloper> call = servInterface.azurirajDevelopera(id,name,address,email);
        if (call != null) {
            call.enqueue(new Callback<WSResponseDeveloper>() {
                @Override
                public void onResponse(Call<WSResponseDeveloper> call, Response<WSResponseDeveloper> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if(listener!=null){
                                listener.onDeveloperUpdate();
                            }
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
