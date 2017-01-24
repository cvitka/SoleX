package hr.foi.air.solex.activities.common;


import java.util.List;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public interface ProjectListingView {
    public void onProjectsArrived(List<ProfileScreenProject> projects);
}
