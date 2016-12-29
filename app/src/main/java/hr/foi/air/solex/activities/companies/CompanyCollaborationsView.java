package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.collaboration.ApiCompanyCollaborations;

import java.util.List;

public interface CompanyCollaborationsView {
    void onDataArrived(List<ApiCompanyCollaborations> collaborationsList);
}
