package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.favorites.ApiFavourites;

import java.util.List;

public interface FavouritesView {
    void dataArrived(List<ApiFavourites> apiFavourites);
}
