package hr.foi.air.solex.models.collaboration;

public interface NeededCollaborationInteractor {
    void addNeededCollaboration(NeededCollaboration neededCollaboration);
    void setAddListener(AddNeededCollaborationListener addNeededCollaborationListener);
}
