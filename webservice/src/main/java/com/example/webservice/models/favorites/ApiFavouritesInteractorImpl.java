package com.example.webservice.models.favorites;

import android.util.Log;

import com.example.webservice.models.WebServiceCommunicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiFavouritesInteractorImpl extends WebServiceCommunicator implements ApiFavouritesInteractor {

    public ApiFavouritesInteractorImpl() {
        initRetrofit();
    }

    private interface WSInterfaceFavorites {

        @GET("dohvatiFavorite.php")
        Call<List<ApiFavourites>> getFavourites(@Query("companyId") int companyId);
    }

    ApiFavouritesListListener mListener;


    @Override
    public void getFavouritesList(int id) {
        WSInterfaceFavorites interfaceCollaborations = retrofit.create(WSInterfaceFavorites.class);
        Call<List<ApiFavourites>> call = interfaceCollaborations.getFavourites(id);
        call.enqueue(new Callback<List<ApiFavourites>>() {
            @Override
            public void onResponse(Call<List<ApiFavourites>> call, Response<List<ApiFavourites>> response) {
                if (response.isSuccessful()) {
                    if (mListener != null) {
                        mListener.dataArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiFavourites>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }



    @Override
    public void setFavouritesListListener(ApiFavouritesListListener apiFavouritesListListener) {
        this.mListener = apiFavouritesListListener;

    }
}
