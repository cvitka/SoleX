package hr.foi.air.solex.models.applicants;

import hr.foi.air.solex.utils.UserType;

public interface ApplicantInteractor {
    /** Dohvati listu aplikanata  */
    void getApplicantList(int collaborationId);
    /** javi da su podatci stigli sa web servisa */
    void setApplicantListListener(ApplicantListListener listener);
}
