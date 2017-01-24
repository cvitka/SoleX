package hr.foi.air.solex.models.favorites;

public interface FavoritesUpdateListener {
    void onUpdate();
    void onUpdateFailure(String message);
}
