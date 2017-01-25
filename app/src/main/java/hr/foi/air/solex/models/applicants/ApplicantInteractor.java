package hr.foi.air.solex.models.applicants;

import hr.foi.air.solex.utils.UserType;

public interface ApplicantInteractor {
    void getApplicantList(int collaborationId);
    void setApplicantListListener(ApplicantListListener listener);
}
