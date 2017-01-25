package hr.foi.air.solex.activities.companies;


import java.util.List;

import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;

public interface CompanyNeededCollaborationView {
    void onCollaborationDataArrived(NeededCollaborationData collaboration);
    void onApplicantsArrived(List<Applicant> list);
    void onSkillsListArrived(List<String> list);
    void onSuccessfullAssign();
}
