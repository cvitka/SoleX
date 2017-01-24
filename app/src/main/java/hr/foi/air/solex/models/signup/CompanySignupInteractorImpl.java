package hr.foi.air.solex.models.signup;

import android.util.Log;

import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Asus on 6.12.2016..
 */

public class CompanySignupInteractorImpl extends WebServiceCommunicator implements CompanySignupInteractor {
    private interface WebServiceInterface {
       @GET("registracijaPoduzeca.php")
       Call<SignUpResponse> registrirajPoduzece(@Query("naziv") String name, @Query("adresa") String address, @Query("email") String email, @Query("lozinka") String password);
    }

    private SignUpResponseListener mResponseListener;
    public CompanySignupInteractorImpl(SignUpResponseListener listener) {
        this.mResponseListener = listener;
        initRetrofit();
    }

    @Override
    public void registerCompany(Company company, String password) {
        WebServiceInterface serviceIntf = retrofit.create(WebServiceInterface.class);
        Call<SignUpResponse> call3 = serviceIntf.registrirajPoduzece(company.getName(), company.getAddress(), company.getEmail(), password);
        if (call3 != null) {
            call3.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            Log.d("API", response.body().getSuccess());
                            //Company.getInstance().setEmail(email);
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

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                    if (mResponseListener != null)
                        mResponseListener.onServerConnectionFailed();
                }
            });
        }
    }
}
