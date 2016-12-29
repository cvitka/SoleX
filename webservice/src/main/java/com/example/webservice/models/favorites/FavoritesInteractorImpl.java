package com.example.webservice.models.favorites;

import android.util.Log;

import com.example.webservice.models.WebServiceCommunicator;
import com.example.webservice.models.login_registration.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FavoritesInteractorImpl extends WebServiceCommunicator implements FavoritesInteractor {

    FavoritesAddListener mFavoritesAddListener;

    private interface WSInterfaceFavourite {

        @GET("dodajFavorita.php")
        Call<WSResponseFavourite> addFavourite(@Query("companyID") int companyID, @Query("developerID") int developerID);

    }

    public FavoritesInteractorImpl() {initRetrofit();
    }

    @Override
    public void addToFavories(int id) {
        WSInterfaceFavourite interfaceFavourite = retrofit.create(WSInterfaceFavourite.class);
        Call<WSResponseFavourite> call = interfaceFavourite.addFavourite(User.getInstance().getId(), id);
        call.enqueue(new Callback<WSResponseFavourite>() {
            @Override
            public void onResponse(Call<WSResponseFavourite> call, Response<WSResponseFavourite> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1")) {
                        if (mFavoritesAddListener != null) {
                            mFavoritesAddListener.onFavoriteAdd();
                        }
                    }else{
                        if(mFavoritesAddListener != null){
                            mFavoritesAddListener.onFavoriteAddFailure(response.body().getMessage());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<WSResponseFavourite> call, Throwable t) {
            }
        });
    }

    @Override
    public void setFavoriteAddListener(FavoritesAddListener addListener) {
        mFavoritesAddListener = addListener;
    }
}
