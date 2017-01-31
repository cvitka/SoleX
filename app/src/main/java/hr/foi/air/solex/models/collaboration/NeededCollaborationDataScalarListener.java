package hr.foi.air.solex.models.collaboration;


public interface NeededCollaborationDataScalarListener {
    /** Listener na potrebne suradnje sa web servisa */
    void neededCollaborationDataArrived(NeededCollaborationData collaborationData);
}
