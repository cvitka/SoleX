package hr.foi.air.solex.presenters.companies;

public interface FavouritesPresenter {
    /**Dohvati favorite */
    void getFavourites(int id);
    /**Dohvati suradnje */
    void getCollaborations();
    /**Dodaj u favorite */
    void addToFavorites(int id);
    /**Azuriraj favorite */
    void updateFavorites(int id);
    /**Obrisi favorita */
    void deleteFavorites(int id);
}
