package hr.foi.air.solex.models.favorites;

import java.util.List;

public interface ApiFavouritesListListener {
    void dataArrived(List<ApiFavourites> apiFavourites);
}
