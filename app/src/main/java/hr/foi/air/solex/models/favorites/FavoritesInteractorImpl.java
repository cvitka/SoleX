package hr.foi.air.solex.models.favorites;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.login_registration.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FavoritesInteractorImpl extends WebServiceCommunicator implements FavoritesInteractor {

    FavoritesAddListener mFavoritesAddListener;
    FavoritesUpdateListener mFavoritesUpdateListener;
    FavoritesDeleteListener mFavoritesDeleteListener;

    /** Definiranje web service end point*/
    private interface WSInterfaceFavourite {

        @GET("dodajFavorita.php")
        Call<WSResponseFavourite> addFavourite(@Query("companyID") int companyID, @Query("developerID") int developerID, @Query("status") int status);

        @GET("updateFavorita.php")
        Call<WSResponseFavourite> updateFavouriteState(@Query("companyID") int companyID, @Query("developerID") int developerID);

        @GET("deleteFavorita.php")
        Call<WSResponseFavourite> deleteFavorit(@Query("developerId") int developerId, @Query("companyId") int companyId);
    }

    public FavoritesInteractorImpl() {
        initRetrofit();
    }

    /** Dodavanje favorita */
    @Override
    public void addToFavorites(int id) {
        WSInterfaceFavourite interfaceFavourite = retrofit.create(WSInterfaceFavourite.class);
        Call<WSResponseFavourite> call = interfaceFavourite.addFavourite(User.getInstance().getId(), id, '1');
        call.enqueue(new Callback<WSResponseFavourite>() {
            @Override
            public void onResponse(Call<WSResponseFavourite> call, Response<WSResponseFavourite> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1")) {
                        if (mFavoritesAddListener != null) {
                            mFavoritesAddListener.onFavoriteAdd();
                        }
                    } else {
                        if (mFavoritesAddListener != null) {
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

    /** Azuriranje favorita  */
    @Override
    public void updateFavorites(int id) {
        WSInterfaceFavourite interfaceFavourite = retrofit.create(WSInterfaceFavourite.class);
        Call<WSResponseFavourite> call = interfaceFavourite.updateFavouriteState(User.getInstance().getId(), id);
        call.enqueue(new Callback<WSResponseFavourite>() {
            @Override
            public void onResponse(Call<WSResponseFavourite> call, Response<WSResponseFavourite> response) {
                if (response.isSuccessful())
                    if (response.body().getSuccess().equals("1")) {
                        if (mFavoritesUpdateListener != null) {
                            mFavoritesUpdateListener.onUpdate();
                        }
                    }else{
                        if(mFavoritesUpdateListener !=null){
                            mFavoritesUpdateListener.onUpdateFailure(response.body().getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(Call<WSResponseFavourite> call, Throwable t) {

            }
        });
    }

    /** Postavljanje listenera */
    @Override
    public void setFavoriteAddListener(FavoritesAddListener addListener) {
        mFavoritesAddListener = addListener;
    }

    @Override
    public void setFavoriteUpdateListener(FavoritesUpdateListener updateListener) {
        mFavoritesUpdateListener = updateListener;
    }

    /** Ukloni iz favorita*/
    @Override
    public void deleteFavorites(int id) {
        WSInterfaceFavourite interfaceFavourite = retrofit.create(WSInterfaceFavourite.class);
        Call<WSResponseFavourite> call = interfaceFavourite.deleteFavorit(id, User.getInstance().getId());
        call.enqueue(new Callback<WSResponseFavourite>() {
            @Override
            public void onResponse(Call<WSResponseFavourite> call, Response<WSResponseFavourite> response) {
                if (response.isSuccessful())
                    if (response.body().getSuccess().equals("1")) {
                        if (mFavoritesDeleteListener != null) {
                            mFavoritesDeleteListener.onFavoriteDelete();
                        }
                    }else{
                        if(mFavoritesDeleteListener !=null){
                            mFavoritesDeleteListener.onFavoriteDeleteFailure(response.body().getMessage());
                        }
                    }
            }

            @Override
            public void onFailure(Call<WSResponseFavourite> call, Throwable t) {

            }
        });
    }

    @Override
    public void setFavoritesDeleteListener(FavoritesDeleteListener deleteListener) {
        mFavoritesDeleteListener = deleteListener;
    }


}
