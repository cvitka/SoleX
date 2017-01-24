package hr.foi.air.solex.models.collaboration;

import java.util.List;

public interface ApiCollaborationsListListener {
    void onProjectListCome(List<ApiCompanyCollaborations> projects);
}
