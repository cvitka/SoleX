package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.collaboration.ApiCollaborationsListListener;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractor;
import hr.foi.air.solex.models.favorites.ApiFavourites;
import hr.foi.air.solex.models.favorites.ApiFavouritesInteractor;
import hr.foi.air.solex.models.favorites.ApiFavouritesListListener;
import hr.foi.air.solex.models.favorites.FavoritesAddListener;
import hr.foi.air.solex.models.favorites.FavoritesDeleteListener;
import hr.foi.air.solex.models.favorites.FavoritesInteractorImpl;
import hr.foi.air.solex.models.favorites.FavoritesUpdateListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.FavouritesActivityView;

public class FavouritesPresenterImpl implements FavouritesPresenter, ApiFavouritesListListener, ApiCollaborationsListListener, FavoritesAddListener, FavoritesUpdateListener, FavoritesDeleteListener {


    FavouritesActivityView mFavouritesActivityView;
    ApiFavouritesInteractor mFavouritesInteractor;
    FavoritesInteractorImpl myFavoritesInteractor;
    ApiCompanyCollaborationsInteractor mApiCompanyCollaborationsInteractor;

    public FavouritesPresenterImpl(FavouritesActivityView mFavouritesActivityView, ApiFavouritesInteractor mFavouritesInteractor, FavoritesInteractorImpl myFavoritesInteractor, ApiCompanyCollaborationsInteractor mApiCompanyCollaborationsInteractor) {
        this.mFavouritesActivityView = mFavouritesActivityView;
        this.mFavouritesInteractor = mFavouritesInteractor;
        this.myFavoritesInteractor = myFavoritesInteractor;
        this.mApiCompanyCollaborationsInteractor = mApiCompanyCollaborationsInteractor;
        mFavouritesInteractor.setFavouritesListListener(this);
        myFavoritesInteractor.setFavoriteAddListener(this);
        myFavoritesInteractor.setFavoriteUpdateListener(this);
        mApiCompanyCollaborationsInteractor.setListListener(this);
        myFavoritesInteractor.setFavoritesDeleteListener(this);
    }



    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {
        mFavouritesActivityView.dataArrived(apiFavourites);

    }

    @Override
    public void getFavourites(int id) {
        mFavouritesInteractor.getFavouritesList(id);

    }

    @Override
    public void getCollaborations() {
        mApiCompanyCollaborationsInteractor.getCollaborationList();

    }

    @Override
    public void addToFavorites(int id) {
        myFavoritesInteractor.addToFavorites(id);
    }

    @Override
    public void updateFavorites(int id) {
        myFavoritesInteractor.updateFavorites(id);
    }

    @Override
    public void deleteFavorites(int id) {
        myFavoritesInteractor.deleteFavorites(id);
    }

    @Override
    public void onProjectListCome(List<ApiCompanyCollaborations> companyCollaborationses) {
        mFavouritesActivityView.onCollabArrived(companyCollaborationses);
    }

    @Override
    public void onFavoriteAdd() {
        mFavouritesActivityView.onFavoriteUpdate();

    }

    @Override
    public void onFavoriteAddFailure(String message) {
        mFavouritesActivityView.onFavoriteFailure(message);
    }

    @Override
    public void onUpdate() {
        mFavouritesActivityView.onFavoriteUpdate();
    }

    @Override
    public void onUpdateFailure(String message) {
        mFavouritesActivityView.onFavoriteUpdateFailure(message);
    }

    @Override
    public void onFavoriteDelete() {
       mFavouritesActivityView.onFavoriteDelete();
    }

    @Override
    public void onFavoriteDeleteFailure(String message) {
        mFavouritesActivityView.onFavoriteUpdateFailure(message);
    }
}
