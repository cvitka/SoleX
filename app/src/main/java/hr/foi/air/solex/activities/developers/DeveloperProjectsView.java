package hr.foi.air.solex.activities.developers;

import java.util.List;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public interface DeveloperProjectsView {
    void onDataArrived(List<ProfileScreenProject> projects);
    void onHighlightAddition();
    void onHighlightFailure(String message);
    void onHighlightRemove();
    void onHighlightRemoveFailure(String message);
}
