package hr.foi.air.solex.presenters;

import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.ApiFavouritesInteractor;
import com.example.webservice.models.favorites.ApiFavouritesListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.FavouritesActivityView;

public class FavouritesPresenterImpl implements FavouritesPresenter, ApiFavouritesListListener {

    FavouritesActivityView mFavouritesActivityView;
    ApiFavouritesInteractor mFavouritesInteractor;

    public FavouritesPresenterImpl(FavouritesActivityView mFavouritesActivityView, ApiFavouritesInteractor mFavouritesInteractor) {
        this.mFavouritesActivityView = mFavouritesActivityView;
        this.mFavouritesInteractor = mFavouritesInteractor;
        mFavouritesInteractor.setFavouritesListListener(this);
    }

    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {
        mFavouritesActivityView.dataArrived(apiFavourites);

    }

    @Override
    public void getFavourites(int id) {
        mFavouritesInteractor.getFavouritesList(id);


    }
}
