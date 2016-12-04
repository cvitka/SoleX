package com.example.webservice.models.Companies;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CompanyModelImpl implements CompanyModel {
    private interface WSInterfaceCompany {

        @GET("dohvatiPoduzece.php")
        Call<WSResponseCompany> dohvatiKompaniju(@Query("id") int id);

        @GET("updatePoduzeca.php")
        Call<WSResponseCompany> azurirajKompaniju(@Query("id") int id, @Query("naziv") String naziv, @Query("adresa") String adresa, @Query("email") String email);
    }

    Retrofit retrofit;
    private final String baseUrl = "http://barka.foi.hr/WebDiP/2015_projekti/WebDiP2015x008/";
    private CompanyScalarListener scalarListener;
    private CompanyUpdateListener updateListener;

    public CompanyModelImpl() {
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void setScalarListener(CompanyScalarListener listener){
        this.scalarListener = listener;
    }

    public void setUpdateListener(CompanyUpdateListener listener){
        this.updateListener = listener;
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
                            if (scalarListener!=null){
                                Company company = new Company();
                                company.setId(response.body().getId());
                                company.setAddress(response.body().getAdresa());
                                company.setName(response.body().getNaziv());
                                company.setEmail(response.body().getEmail());
                                company.setPicture(response.body().getSlika());
                                scalarListener.onDataCome(company);
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

    @Override
    public void updateCompanyData(Company company) {
        WSInterfaceCompany servInterface = retrofit.create(WSInterfaceCompany.class);
        Call<WSResponseCompany> call = servInterface.azurirajKompaniju(company.getId(),company.getName(),company.getAddress(), company.getEmail());
        if (call != null) {
            call.enqueue(new Callback<WSResponseCompany>() {
                @Override
                public void onResponse(Call<WSResponseCompany> call, Response<WSResponseCompany> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if(updateListener!=null){
                                updateListener.onCompanyUpdate();
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
