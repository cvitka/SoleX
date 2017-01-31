package hr.foi.air.solex.models.applicants;


import java.util.List;

import hr.foi.air.solex.models.collab_applicat.ApplicatListListener;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

/** Listener za listu podataka sa web servisa */
public interface ApplicantListListener {
    void onApplicantListCome(List<Applicant> applicants);
}
