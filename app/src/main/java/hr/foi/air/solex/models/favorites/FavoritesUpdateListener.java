package hr.foi.air.solex.models.favorites;

/** Listener za azuriranje developera i povratna poruka u slucaju neuspjeha*/
public interface FavoritesUpdateListener {
    void onUpdate();
    void onUpdateFailure(String message);
}
