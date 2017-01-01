package com.example.webservice.models.favorites;

public interface FavoritesInteractor {
    void addToFavorites(int id);
    void updateFavorites(int id);
    void setFavoriteAddListener(FavoritesAddListener addListener);
    void setFavoriteUpdateListener(FavoritesUpdateListener updateListener);
}
