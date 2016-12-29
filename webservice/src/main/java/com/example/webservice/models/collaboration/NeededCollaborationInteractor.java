package com.example.webservice.models.collaboration;

public interface NeededCollaborationInteractor {
    void addNeededCollaboration(NeededCollaboration neededCollaboration);
    void setAddListener(AddNeededCollaborationListener addNeededCollaborationListener);
}
