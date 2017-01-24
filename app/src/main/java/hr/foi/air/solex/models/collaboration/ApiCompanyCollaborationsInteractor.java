package hr.foi.air.solex.models.collaboration;

public interface ApiCompanyCollaborationsInteractor {
    void setListListener(ApiCollaborationsListListener listListener);
    void setScalarListener(ApiCompanyCollaborationScalarListener scalarListener);
    void getCollaborationList();
    void getCollaborationScalar(int collaborationId);

}
