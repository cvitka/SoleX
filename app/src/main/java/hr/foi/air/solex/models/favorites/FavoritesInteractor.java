package hr.foi.air.solex.models.favorites;

public interface FavoritesInteractor {
    /**Dodaj favorita */
    void addToFavorites(int id);
    /** Azuriraj favorita*/
    void updateFavorites(int id);
    /**Javi da se favorit dodan */
    void setFavoriteAddListener(FavoritesAddListener addListener);
    /**Javi da je favorit azuriran */
    void setFavoriteUpdateListener(FavoritesUpdateListener updateListener);
    /**Ukloni favorita */
    void deleteFavorites(int id);
    /**Javi da je favorit uklonjen */
    void setFavoritesDeleteListener(FavoritesDeleteListener deleteListener);
}
