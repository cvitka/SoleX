package hr.foi.air.solex.presenters.companies;


public interface CompanyNeededCollaborationPresenter {
    /**Dohvati aplikante */
    void getApplicants(int collaborationId);
    /**Dohvati kompetencije suradnje */
    void getCollaborationSkills(int collaborationId);
    /**Odaberi aplikanta */
    void applicantChosen(int collaborationId, int applicantId);
    /**Dohvati podatke za suradnju */
    void getCollaborationData(int collaborationId);
    /**Push notification */
    void pushNotification(int developerId);
}
