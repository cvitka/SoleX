package com.example.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tomislav on 11/30/16.
 */

public interface WSInterfaceCompany {

    @GET("updatePoduzeca.php")
    Call<WSResponseCompany> azurirajKompaniju(@Query("id") int id, @Query("naziv") String naziv, @Query("adresa") String adresa, @Query("email") String email);
}
