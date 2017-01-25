package hr.foi.air.solex.models.collab_applicat;


public interface CollabApplicatInteractor {
    void getCollaborations(int developerId);
    void getApplications(int developerId);
    void setCollabListener(CollabListListener collabListListener);
    void setApplicatListener(ApplicatListListener applicatListListener);
}
