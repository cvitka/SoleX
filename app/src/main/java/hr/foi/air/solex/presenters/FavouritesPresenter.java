package hr.foi.air.solex.presenters;

public interface FavouritesPresenter {
    void getFavourites(int id);
    void getCollaborations();
    void addToFavorites(int id);
    void updateFavorites(int id);
    void deleteFavorites(int id);
}
