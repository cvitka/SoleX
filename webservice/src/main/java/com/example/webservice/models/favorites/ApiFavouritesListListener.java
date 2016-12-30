package com.example.webservice.models.favorites;

import java.util.List;

public interface ApiFavouritesListListener {
    void dataArrived(List<ApiFavourites> apiFavourites);
}
