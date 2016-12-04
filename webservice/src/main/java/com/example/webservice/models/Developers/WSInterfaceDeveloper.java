package com.example.webservice.models.Developers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WSInterfaceDeveloper {

    @GET("dohvatiDeveloepre.php")
    Call<WSResponseDeveloper> dohvatiDevelopere(@Query("id") int id);

    @GET("updateDevelopera.php")
    Call<WSResponseDeveloper> azurirajDevelopera(@Query("id") int id, @Query("ime") String ime, @Query("adresa") String adresa, @Query("email") String email);
}
