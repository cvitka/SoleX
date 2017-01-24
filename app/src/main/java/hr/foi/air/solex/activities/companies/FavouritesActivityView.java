package hr.foi.air.solex.activities.companies;

import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.favorites.ApiFavourites;

import java.util.List;

public interface FavouritesActivityView {
    void dataArrived(List<ApiFavourites> apiFavourites);
    void onCollabArrived(List<ApiCompanyCollaborations> apiCompanyCollaborationses);
    void onFavoriteAddition();
    void onFavoriteFailure(String message);
    void onFavoriteUpdate();
    void onFavoriteUpdateFailure(String message);
    void onFavoriteDelete();
    void onFavoriteDeleteFailure(String message);
}
