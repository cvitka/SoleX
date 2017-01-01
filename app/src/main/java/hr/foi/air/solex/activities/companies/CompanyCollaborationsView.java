package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.WSResponseFavourite;

import java.util.List;

public interface CompanyCollaborationsView {
    void onDataArrived(List<ApiCompanyCollaborations> collaborationsList);
    void onFavoriteAddition();
    void onFavoriteFailure(String message);
    void onFavoriteUpdate();
    void onFavoriteUpdateFailure(String message);
}
