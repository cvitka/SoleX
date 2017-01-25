package hr.foi.air.solex.models.applicants;


import java.util.List;

import hr.foi.air.solex.models.collab_applicat.ApplicatListListener;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public interface ApplicantListListener {
    void onApplicantListCome(List<Applicant> applicants);
}
