package hr.foi.air.solex.models.favorites;

public interface FavoritesAddListener {
    void onFavoriteAdd();
    void onFavoriteAddFailure(String message);
}
