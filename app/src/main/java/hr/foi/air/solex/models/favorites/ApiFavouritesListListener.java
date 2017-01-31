package hr.foi.air.solex.models.favorites;

import java.util.List;

/**Listener za listu favorita */
public interface ApiFavouritesListListener {
    void dataArrived(List<ApiFavourites> apiFavourites);
}
