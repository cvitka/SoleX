package hr.foi.air.solex.models.favorites;

public interface FavoritesInteractor {
    void addToFavorites(int id);
    void updateFavorites(int id);
    void setFavoriteAddListener(FavoritesAddListener addListener);
    void setFavoriteUpdateListener(FavoritesUpdateListener updateListener);
    void deleteFavorites(int id);
    void setFavoritesDeleteListener(FavoritesDeleteListener deleteListener);
}
