package hr.foi.air.solex.models.favorites;


public interface FavoritesDeleteListener {
    void onFavoriteDelete();
    void onFavoriteDeleteFailure(String message);
}
