package com.example.webservice.models.favorites;

public interface FavoritesUpdateListener {
    void onUpdate();
    void onUpdateFailure(String message);
}
