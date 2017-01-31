package hr.foi.air.solex.models.collaboration;

/** Listener za dobivenu listu podataka sa web servisa */
public interface ApiCompanyCollaborationScalarListener {
    void companyCollaborationArrived(ApiCompanyCollaborations  collaboration);
}
