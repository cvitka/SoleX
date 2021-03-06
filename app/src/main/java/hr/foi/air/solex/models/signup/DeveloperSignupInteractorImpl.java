package hr.foi.air.solex.models.signup;

import android.util.Log;

import hr.foi.air.solex.models.mdevelopers.Developer;
import hr.foi.air.solex.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class DeveloperSignupInteractorImpl extends WebServiceCommunicator implements DeveloperSignupInteractor {

    /**Definiranje web service end point-a */
    private interface WebServiceInterface {
        @GET("registracijaDevelopera.php")
        Call<SignUpResponse> registrirajDevelopera(@Query("ime") String name, @Query("prezime") String surName, @Query("adresa") String address, @Query("email") String email, @Query("lozinka") String password,@Query("token") String token);
    }

    private SignUpResponseListener mResponseListener;
    public DeveloperSignupInteractorImpl(SignUpResponseListener listener) {
        this.mResponseListener = listener;
        initRetrofit();
    }

    /**Registriraj developera */
    @Override
    public void registerDeveloper(Developer developer, String password) {
        WebServiceInterface serviceIntf = retrofit.create(WebServiceInterface.class);
        Call<SignUpResponse> call2 = serviceIntf.registrirajDevelopera(developer.getIme(), developer.getPrezime(), developer.getAdresa(), developer.getEmail(), password, developer.getToken());
        if (call2 != null) {
            call2.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());
                            if (mResponseListener != null) {
                                mResponseListener.onRegistrationSuccessful();
                            }
                        } else {
                            if (mResponseListener != null) {
                                mResponseListener.onRegistrationFailed(response.body().getMessage());
                            }
                        }
                    }
                }

                /**javi da je doslo do pogreske prilikom registracije */
                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    mResponseListener.onServerConnectionFailed();
                }
            });
        }
    }
}
