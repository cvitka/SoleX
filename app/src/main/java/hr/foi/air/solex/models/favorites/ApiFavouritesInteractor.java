package hr.foi.air.solex.models.favorites;

public interface ApiFavouritesInteractor {
    /**Dohvati listu favorita za poduzece */
    void getFavouritesList(int id);
    /**javi da su favoriti dohvaceni */
    void setFavouritesListListener(ApiFavouritesListListener apiFavouritesListListener);
}
