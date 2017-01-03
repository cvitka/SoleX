package com.example.webservice.models.favorites;


public interface FavoritesDeleteListener {
    void onFavoriteDelete();
    void onFavoriteDeleteFailure(String message);
}
