package hr.foi.air.solex.presenters;

import com.example.webservice.models.favorites.FavoritesAddListener;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;

import hr.foi.air.solex.activities.companies.CompanyAddFavoriteView;

public class CompanyAddFavoritePresenterImpl implements CompanyAddFavoritePresenter,FavoritesAddListener {
    CompanyAddFavoriteView mCompanyAddFavoriteView;
    FavoritesInteractorImpl mFavoritesInteractor;

    public CompanyAddFavoritePresenterImpl(CompanyAddFavoriteView companyAddFavoriteView, FavoritesInteractorImpl favoritesInteractor) {
        this.mCompanyAddFavoriteView = companyAddFavoriteView;
        this.mFavoritesInteractor = favoritesInteractor;
        mFavoritesInteractor.setFavoriteAddListener(this);
    }


    @Override
    public void addToFavorites(int id) {
        mFavoritesInteractor.addToFavories(id);
    }

    @Override
    public void onFavoriteAdd() {
        mCompanyAddFavoriteView.onFavoriteAddition();
    }

    @Override
    public void onFavoriteAddFailure(String message) {
        mCompanyAddFavoriteView.onFavoriteFailure(message);
    }
}
