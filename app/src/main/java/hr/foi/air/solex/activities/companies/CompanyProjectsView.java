package hr.foi.air.solex.activities.companies;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

public interface CompanyProjectsView {
    void onDataArrived(List<ProfileScreenProject> projects);
    void onHighlightAddition();
    void onHighlightFailure(String message);
    void onHighlightRemove();
    void onHighlightRemoveFailure(String message);
}
