package hr.foi.air.solex.models.collaboration;

import hr.foi.air.solex.models.needed_collab.*;

public interface ApiNeededCollaborationsInteractor {
    void getData(int id);
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);
    void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener);
    void getNeededCollaboration(int collaborationId);
}
