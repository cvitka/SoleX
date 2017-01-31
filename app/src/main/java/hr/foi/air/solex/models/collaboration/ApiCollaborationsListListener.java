package hr.foi.air.solex.models.collaboration;

import java.util.List;

/** Listener za listu podataka sa web servisa */
public interface ApiCollaborationsListListener {
    void onProjectListCome(List<ApiCompanyCollaborations> projects);
}
