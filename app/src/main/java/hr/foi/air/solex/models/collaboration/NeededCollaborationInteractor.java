package hr.foi.air.solex.models.collaboration;

public interface NeededCollaborationInteractor {
    /**Dodaj potrebnu suradnju */
    void addNeededCollaboration(NeededCollaboration neededCollaboration);
    /** Javi da je potrebna suradnja dodana*/
    void setAddListener(AddNeededCollaborationListener addNeededCollaborationListener);
}
