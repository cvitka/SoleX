package hr.foi.air.solex.presenters.companies;


public interface CompanyNeededCollaborationPresenter {
    void getApplicants(int collaborationId);
    void getCollaborationSkills(int collaborationId);
    void applicantChosen(int collaborationId, int applicantId);
    void getCollaborationData(int collaborationId);
    void pushNotification(int developerId);
}
