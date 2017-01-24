package hr.foi.air.solex.activities.companies;

import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;

import java.util.List;

public interface CompanyCollaborationsView {
    void onDataArrived(List<ApiCompanyCollaborations> collaborationsList);
    void onFavoriteAddition();
    void onFavoriteFailure(String message);
    void onFavoriteUpdate();
    void onFavoriteUpdateFailure(String message);
}
