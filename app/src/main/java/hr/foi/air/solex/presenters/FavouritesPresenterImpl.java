package hr.foi.air.solex.presenters;

import com.example.webservice.models.collaboration.ApiCollaborationsListListener;
import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractor;
import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.ApiFavouritesInteractor;
import com.example.webservice.models.favorites.ApiFavouritesListListener;
import com.example.webservice.models.favorites.FavoritesAddListener;
import com.example.webservice.models.favorites.FavoritesDeleteListener;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;
import com.example.webservice.models.favorites.FavoritesUpdateListener;

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
