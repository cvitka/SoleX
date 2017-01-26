package hr.foi.air.solex.presenters.developers;

public interface DeveloperNeededCollaborationPresenter {
    void getCollaborationData(int collaborationId, int developerId);
    void getCollaborationSkills(int collaborationId);
    void apply(int collaborationId, int developerId);
    void removeApply(int collaborationId, int developerId);
}
