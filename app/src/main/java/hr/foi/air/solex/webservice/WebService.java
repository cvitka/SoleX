package hr.foi.air.solex.webservice;

import hr.foi.air.solex.models.Developers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by cvitka on 10.11.16..
 */

public interface WebService {

    @GET("prijava.php")
    Call<WebServiceResponse> checkPrijava(@Query("email") String email, @Query("lozinka") String password);

    @FormUrlEncoded
    @POST("registacijaDeveloperi.php")
    Call<WebServiceResponse> registrirajDeveloper(@Field("method") String email, String password);

    @FormUrlEncoded
    @POST("registacijaPoduzeca.php")
    Call<WebServiceResponse> registrirajPoduzece(@Field("method") String method, String password);
}
