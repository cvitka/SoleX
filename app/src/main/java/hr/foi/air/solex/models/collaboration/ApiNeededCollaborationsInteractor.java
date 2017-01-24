package hr.foi.air.solex.models.collaboration;

public interface ApiNeededCollaborationsInteractor {
    void getData(int id);
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);
}
