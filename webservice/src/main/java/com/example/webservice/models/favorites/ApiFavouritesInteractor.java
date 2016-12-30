package com.example.webservice.models.favorites;

public interface ApiFavouritesInteractor {
    void getFavouritesList(int id);
    void setFavouritesListListener(ApiFavouritesListListener apiFavouritesListListener);
}
