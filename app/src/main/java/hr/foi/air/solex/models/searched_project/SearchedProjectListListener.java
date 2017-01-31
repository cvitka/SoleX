package hr.foi.air.solex.models.searched_project;


import java.util.List;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

/**Listener za listu projekata */
public interface SearchedProjectListListener {
    void onProjectListCome(List<SearchedProject> projects);
}
