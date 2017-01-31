package hr.foi.air.solex.models.collaboration;

public interface ApiCompanyCollaborationsInteractor {
    /**Postavljanje list listenera */
    void setListListener(ApiCollaborationsListListener listListener);
    /**Postavljanje scalar listenera */
    void setScalarListener(ApiCompanyCollaborationScalarListener scalarListener);
    /**Postavljanje listenera za dohvat liste podataka */
    void getCollaborationList();
    /**Postavljanje listenera za dohvat podataka */
    void getCollaborationScalar(int collaborationId);

}
