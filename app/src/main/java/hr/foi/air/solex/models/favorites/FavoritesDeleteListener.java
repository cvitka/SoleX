package hr.foi.air.solex.models.favorites;

/**Listener za uklanjanje favorita i povratna poruka */
public interface FavoritesDeleteListener {
    void onFavoriteDelete();
    void onFavoriteDeleteFailure(String message);
}
