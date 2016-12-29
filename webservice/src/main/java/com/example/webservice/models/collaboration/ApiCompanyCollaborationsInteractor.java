package com.example.webservice.models.collaboration;

public interface ApiCompanyCollaborationsInteractor {
    void setListListener(ApiCollaborationsListListener listListener);

    void getCollaborationList();

}
