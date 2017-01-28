package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.collaboration.ApiCollaborationsListListener;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractor;
import hr.foi.air.solex.models.favorites.FavoritesAddListener;
import hr.foi.air.solex.models.favorites.FavoritesInteractor;
import hr.foi.air.solex.models.favorites.FavoritesInteractorImpl;
import hr.foi.air.solex.models.favorites.FavoritesUpdateListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyCollaborationsView;
import hr.foi.air.solex.models.ratings.RatingsInteractor;
import hr.foi.air.solex.models.ratings.RatingsInteractorImpl;
import hr.foi.air.solex.models.ratings.RatingsListener;

public class CompanyCollaborationsPresenterImpl implements CompanyCollaborationsPresenter, ApiCollaborationsListListener, FavoritesAddListener, FavoritesUpdateListener, RatingsListener {

    private CompanyCollaborationsView mCompanyCollaborationsView;
    private ApiCompanyCollaborationsInteractor mApiCompanyCollaborationsInteractor;
    private FavoritesInteractor mFavoritesInteractor;
    private RatingsInteractor mRatingsInteractor;

    public CompanyCollaborationsPresenterImpl(CompanyCollaborationsView companyCollaborationsView, ApiCompanyCollaborationsInteractor apiCompanyCollaborationsInteractor, FavoritesInteractorImpl favoritesInteractor) {
        this.mCompanyCollaborationsView = companyCollaborationsView;
        this.mApiCompanyCollaborationsInteractor = apiCompanyCollaborationsInteractor;
        this.mFavoritesInteractor = favoritesInteractor;
        mApiCompanyCollaborationsInteractor.setListListener(this);
        mFavoritesInteractor.setFavoriteAddListener(this);
        mFavoritesInteractor.setFavoriteUpdateListener(this);
        mRatingsInteractor = new RatingsInteractorImpl(this);
    }

    @Override
    public void getCollaborations() {

        mApiCompanyCollaborationsInteractor.getCollaborationList();
    }

    @Override
    public void addToFavorites(int id) {
        mFavoritesInteractor.addToFavorites(id);
    }

    @Override
    public void updateFavorites(int id) {
        mFavoritesInteractor.updateFavorites(id);
    }


    @Override
    public void onProjectListCome(List<ApiCompanyCollaborations> collaborations) {
        mCompanyCollaborationsView.onDataArrived(collaborations);
    }

    @Override
    public void onFavoriteAdd() {
        mCompanyCollaborationsView.onFavoriteAddition();
    }

    @Override
    public void onFavoriteAddFailure(String message) {
        mCompanyCollaborationsView.onFavoriteFailure(message);
    }

    @Override
    public void onUpdate() {
        mCompanyCollaborationsView.onFavoriteUpdate();
    }

    @Override
    public void onUpdateFailure(String message) {
        mCompanyCollaborationsView.onFavoriteUpdateFailure(message);
    }

    @Override
    public void rate(int rating, int user, int collaborationId) {
        mRatingsInteractor.rate(rating, user, collaborationId);
    }

    @Override
    public void onRatingSucceeded() {
        //mView.suceeded....
        mCompanyCollaborationsView.onRateSucceeded();
    }
}
