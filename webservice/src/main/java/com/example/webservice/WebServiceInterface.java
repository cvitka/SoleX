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

    @GET("registacijaDeveloperi.php")
    Call<WebServiceResponse> registrirajDeveloper(@Query("method") String email, String password);

    @GET("registacijaPoduzeca.php")
    Call<WebServiceResponse> registrirajPoduzece(@Query("method") String method, String password);
}
