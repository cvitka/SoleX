package com.example.webservice.models.collaboration;

import android.util.Log;

import com.example.webservice.models.WebServiceCommunicator;
import com.example.webservice.models.login_registration.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiCompanyCollaborationsInteractorImpl extends WebServiceCommunicator implements ApiCompanyCollaborationsInteractor {
    ApiCollaborationsListListener mListListener;

    private interface WSInterfaceCollaborations {

        @GET("dohvatiSuradnje.php")
        Call<List<ApiCompanyCollaborations>> getCollaborations(@Query("companyID") int companyID);
    }

    public ApiCompanyCollaborationsInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void setListListener(ApiCollaborationsListListener listListener) {
        mListListener = listListener;
    }

    @Override
    public void getCollaborationList() {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<List<ApiCompanyCollaborations>> call = interfaceCollaborations.getCollaborations(User.getInstance().getId());
        call.enqueue(new Callback<List<ApiCompanyCollaborations>>() {
            @Override
            public void onResponse(Call<List<ApiCompanyCollaborations>> call, Response<List<ApiCompanyCollaborations>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiCompanyCollaborations>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }



}
