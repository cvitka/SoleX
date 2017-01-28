package hr.foi.air.solex.models.profile_screen_project;

import hr.foi.air.solex.utils.UserType;

public interface ProfileScreenProjectInteractor {
    void getHighlightedProjectList(int id, UserType userType);
    void getAllProjectList(int id);

    void getAllProjectList(int id, UserType userType);

    void addToHighlighted(int projectID, int userId, UserType userType);
    void removeHighlighted(int projectID, int userId, UserType userType);

    void setAddHighlightListener(AddHighlightListener listener);
    void setRemoveHighlightListener(RemoveHighlightListener listener);

    void getProjectList();

}
