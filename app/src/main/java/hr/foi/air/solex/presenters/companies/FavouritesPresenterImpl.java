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


    /**Javi viewu da su favoriti stigli*/
    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {
        mFavouritesActivityView.dataArrived(apiFavourites);

    }

    /**Javi interactoru da dohvati favorite */
    @Override
    public void getFavourites(int id) {
        mFavouritesInteractor.getFavouritesList(id);

    }

    /**Javi interactoru da dohvati suradnje*/
    @Override
    public void getCollaborations() {
        mApiCompanyCollaborationsInteractor.getCollaborationList();

    }

    /**Javi interactoru da doda u favorite projekt */
    @Override
    public void addToFavorites(int id) {
        myFavoritesInteractor.addToFavorites(id);
    }

    /**Javi interactoru da azurira projekt u favoritima */
    @Override
    public void updateFavorites(int id) {
        myFavoritesInteractor.updateFavorites(id);
    }

    /**Javi interactoru da obrise projekt iz favorita*/
    @Override
    public void deleteFavorites(int id) {
        myFavoritesInteractor.deleteFavorites(id);
    }

    /**Javi viewu da su suradnje stigle*/
    @Override
    public void onProjectListCome(List<ApiCompanyCollaborations> companyCollaborationses) {
        mFavouritesActivityView.onCollabArrived(companyCollaborationses);
    }

    /**Javi viewu da je favorit dodan*/
    @Override
    public void onFavoriteAdd() {
        mFavouritesActivityView.onFavoriteUpdate();

    }

    /**Javi viewu da favorit niej dodan*/
    @Override
    public void onFavoriteAddFailure(String message) {
        mFavouritesActivityView.onFavoriteFailure(message);
    }

    /**Javi viewu da je favorit azuriran*/
    @Override
    public void onUpdate() {
        mFavouritesActivityView.onFavoriteUpdate();
    }

    /**Javi viewu da azuriranje nije uspjelo*/
    @Override
    public void onUpdateFailure(String message) {
        mFavouritesActivityView.onFavoriteUpdateFailure(message);
    }

    /**Javi viewu da je favorit obrisan*/
    @Override
    public void onFavoriteDelete() {
       mFavouritesActivityView.onFavoriteDelete();
    }

    /**Javi viewu da brisanje favorita nije uspjelo*/
    @Override
    public void onFavoriteDeleteFailure(String message) {
        mFavouritesActivityView.onFavoriteUpdateFailure(message);
    }
}
