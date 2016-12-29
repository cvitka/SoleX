package com.example.webservice.models.favorites;

public interface FavoritesInteractor {
    void addToFavories(int id);
    void setFavoriteAddListener(FavoritesAddListener addListener);
}
