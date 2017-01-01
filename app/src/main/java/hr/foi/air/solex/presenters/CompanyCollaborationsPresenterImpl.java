package hr.foi.air.solex.presenters;

import com.example.webservice.models.collaboration.ApiCollaborationsListListener;
import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractor;
import com.example.webservice.models.favorites.FavoritesAddListener;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;
import com.example.webservice.models.favorites.FavoritesUpdateListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyCollaborationsView;

public class CompanyCollaborationsPresenterImpl implements CompanyCollaborationsPresenter, ApiCollaborationsListListener, FavoritesAddListener, FavoritesUpdateListener {

    CompanyCollaborationsView mCompanyCollaborationsView;
    ApiCompanyCollaborationsInteractor mApiCompanyCollaborationsInteractor;
    FavoritesInteractorImpl mFavoritesInteractor;

    public CompanyCollaborationsPresenterImpl(CompanyCollaborationsView companyCollaborationsView, ApiCompanyCollaborationsInteractor apiCompanyCollaborationsInteractor, FavoritesInteractorImpl favoritesInteractor) {
        this.mCompanyCollaborationsView = companyCollaborationsView;
        this.mApiCompanyCollaborationsInteractor = apiCompanyCollaborationsInteractor;
        this.mFavoritesInteractor = favoritesInteractor;
        mApiCompanyCollaborationsInteractor.setListListener(this);
        mFavoritesInteractor.setFavoriteAddListener(this);
        mFavoritesInteractor.setFavoriteUpdateListener(this);
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
}
