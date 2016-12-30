package com.example.webservice.models.collaboration;

public interface ApiNeededCollaborationsInteractor {
    void getData(int id);
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);
}
