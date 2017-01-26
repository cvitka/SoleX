package hr.foi.air.solex.models.collaboration;

import hr.foi.air.solex.models.needed_collab.*;
import hr.foi.air.solex.presenters.companies.ApplicationAcceptedListener;

public interface ApiNeededCollaborationsInteractor {
    void getData(int id);
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);
    void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener);
    void setApplicationAcceptedListener(ApplicationAcceptedListener listener);
    void getNeededCollaboration(int collaborationId);
    void applicationAccepted(int collaborationId, int developerId);
}
