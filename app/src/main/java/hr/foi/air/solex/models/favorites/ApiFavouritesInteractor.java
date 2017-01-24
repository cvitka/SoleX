package hr.foi.air.solex.models.favorites;

public interface ApiFavouritesInteractor {
    void getFavouritesList(int id);
    void setFavouritesListListener(ApiFavouritesListListener apiFavouritesListListener);
}
