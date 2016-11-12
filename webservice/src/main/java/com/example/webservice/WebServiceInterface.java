package com.example.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cvitka on 11.11.16..
 */

public interface WebServiceInterface {
    @GET("prijava.php")
    Call<WebServiceResponse> checkPrijava(@Query("email") String email, @Query("lozinka") String password);

    @GET("registracijaDevelopera.php")
    Call<WebServiceResponse> registrirajDevelopera(@Query("ime") String name, @Query("prezime") String surName, @Query("adresa") String address, @Query("email") String email, @Query("lozinka") String password );

    @GET("registracijaPoduzeca.php")
    Call<WebServiceResponse> registrirajPoduzece(@Query("naziv") String name, @Query("adresa") String address, @Query("email") String email, @Query("lozinka") String password );
}
