package com.example.webservice.models.developers;

import android.util.Log;

import com.example.webservice.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class DeveloperInteractorImpl extends WebServiceCommunicator implements DeveloperInteractor{
    private interface WSInterfaceDeveloper {

        @GET("dohvatiDeveloepre.php")
        Call<WSResponseDeveloper> dohvatiDevelopere(@Query("id") int id);

        @GET("updateDevelopera.php")
        Call<WSResponseDeveloper> azurirajDevelopera(@Query("id") int id, @Query("ime") String ime, @Query("prezime") String prezime, @Query("adresa") String adresa, @Query("email") String email,@Query("kontaktBroj") String kontaktBroj,@Query("godineIskustva") String godineIskustva,@Query("slika") String slika);
    }

    private DeveloperScalarListener scalarListener;
    private DeveloperUpdateListener updateListener;
/*
    public DeveloperInteractorImpl(DeveloperScalarListener listener) {
        this.listener = listener;
        initRetrofit();
    }
*/
    public DeveloperInteractorImpl(){
        initRetrofit();
    }

    public void getDeveloperListData(int [] id){//neki ulazni parametar koji već nam bude trebao, možemo imat više razl. metoda za dohvat liste po različitim parametrima

    }


    public void setScalarListener(DeveloperScalarListener listener){
        this.scalarListener = listener;
    }

    public void setUpdateListener(DeveloperUpdateListener listener){
        this.updateListener = listener;
    }

    @Override
    public void getDeveloperData(int id){
        Log.v("", scalarListener.toString());
        WSInterfaceDeveloper interfaceCompany = retrofit.create(WSInterfaceDeveloper.class);
        Call<WSResponseDeveloper> call = interfaceCompany.dohvatiDevelopere(id);
        if (call!=null)
        {
            call.enqueue(new Callback<WSResponseDeveloper>() {
                @Override
                public void onResponse(Call<WSResponseDeveloper> call, Response<WSResponseDeveloper> response) {

                    Log.w("no haha", "ha = ha");
                    if(response.isSuccessful())
                    {
                        if (scalarListener != null)
                        {
                            Developer developer = new Developer();
                            developer.setId(response.body().getId());
                            developer.setIme(response.body().getIme());
                            developer.setPrezime(response.body().getPrezime());
                            developer.setAdresa(response.body().getAdresa());
                            developer.setEmail(response.body().getEmail());
                            developer.setKontaktBroj(response.body().getKontaktBroj());
                            developer.setGodineIskustva(response.body().getGodineIskustva());
                            developer.setPicture(response.body().getSlika());
                            scalarListener.onDataComeDeveloper(developer);
                            //response.body().
                        }
                    }
                    else
                        Log.w("no listener", "listener = null");
                }
                @Override
                public void onFailure(Call<WSResponseDeveloper> call, Throwable t) {
                    Log.w("onFailure", t.getMessage());
                }
            });
        }
    }

    @Override
    public void updateDeveloperData(int id, String name,String surname, String address, String email,String number,String years,String image) {
        WSInterfaceDeveloper servInterface = retrofit.create(WSInterfaceDeveloper.class);
        Call<WSResponseDeveloper> call = servInterface.azurirajDevelopera(id, name, surname, address, email,number,years,image);
        if (call != null) {
            call.enqueue(new Callback<WSResponseDeveloper>() {
                @Override
                public void onResponse(Call<WSResponseDeveloper> call, Response<WSResponseDeveloper> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            if (updateListener != null) {
                                updateListener.onDeveloperUpdate();
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
