package hr.foi.air.solex.presenters;

import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.ApiFavouritesInteractor;
import com.example.webservice.models.favorites.ApiFavouritesListListener;
import com.example.webservice.models.favorites.FavoritesInteractor;

import java.util.List;

import hr.foi.air.solex.activities.companies.FavouritesView;

public class FavouritesPresenterImpl implements FavouritesPresenter, ApiFavouritesListListener {

    FavouritesView mFavouritesView;
    ApiFavouritesInteractor mFavouritesInteractor;

    public FavouritesPresenterImpl(FavouritesView mFavouritesView, ApiFavouritesInteractor mFavouritesInteractor) {
        this.mFavouritesView = mFavouritesView;
        this.mFavouritesInteractor = mFavouritesInteractor;
        mFavouritesInteractor.setFavouritesListListener(this);
    }

    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {
        mFavouritesView.dataArrived(apiFavourites);

    }

    @Override
    public void getFavourites(int id) {
        mFavouritesInteractor.getFavouritesList(id);


    }
}
