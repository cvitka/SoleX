package hr.foi.air.solex.models.collab_applicat;


public interface CollabApplicatInteractor {
    /**Dohvati kolaboracije za odredenog developera*/
    void getCollaborations(int developerId);
    /**Dohvati apliciranja odredenog developera*/
    void getApplications(int developerId);
    /**Javi da su kolaboracije stigle*/
    void setCollabListener(CollabListListener collabListListener);
    /**Javi da su apliciranja stigla*/
    void setApplicatListener(ApplicatListListener applicatListListener);
}
