package hr.foi.air.solex.models.searched_project;


import java.util.List;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public interface SearchedProjectListListener {
    void onProjectListCome(List<SearchedProject> projects);
}
