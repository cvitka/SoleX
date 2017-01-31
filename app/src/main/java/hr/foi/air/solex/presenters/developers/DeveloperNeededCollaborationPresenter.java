package hr.foi.air.solex.presenters.developers;

public interface DeveloperNeededCollaborationPresenter {
    /**Dohvati podatke suradnje */
    void getCollaborationData(int collaborationId, int developerId);
    /**Dohvati kompetencije suradnje */
    void getCollaborationSkills(int collaborationId);
    /**Prihvati */
    void apply(int collaborationId, int developerId);
    /**Odbij apliciranje */
    void removeApply(int collaborationId, int developerId);
    /**Push obavijest */
    void pushNotif(int companyId);
}
