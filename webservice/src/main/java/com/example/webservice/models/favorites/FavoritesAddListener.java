package com.example.webservice.models.favorites;

public interface FavoritesAddListener {
    void onFavoriteAdd();
    void onFavoriteAddFailure(String message);
}
