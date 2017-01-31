package hr.foi.air.solex.models.favorites;

/**Listener za dodavanje favorita i povratna poruka */
public interface FavoritesAddListener {
    void onFavoriteAdd();
    void onFavoriteAddFailure(String message);
}
