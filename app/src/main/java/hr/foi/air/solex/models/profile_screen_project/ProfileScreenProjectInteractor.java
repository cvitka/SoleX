package hr.foi.air.solex.models.profile_screen_project;

import com.example.core.utils.UserType;

public interface ProfileScreenProjectInteractor {
    void getHighlightedProjectList(int id, UserType userType);
    void getAllProjectList(int id);
}
