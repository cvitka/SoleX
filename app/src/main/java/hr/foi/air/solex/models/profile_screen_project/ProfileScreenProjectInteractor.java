package hr.foi.air.solex.models.profile_screen_project;

import hr.foi.air.solex.utils.UserType;

public interface ProfileScreenProjectInteractor {

    /**Dohvati istaknute projekte poduzeca */
    void getHighlightedProjectList(int id, UserType userType);
    /**Dohvati listu svih projekata */
    void getAllProjectList(int id);
    /**Dohvati listu svih projekata ali po tipu korisnika */
    void getAllProjectList(int id, UserType userType);
    /**Dodaj projekt u istaknute */
    void addToHighlighted(int projectID, int userId, UserType userType);
    /**Ukloni projekt iz istaknutih */
    void removeHighlighted(int projectID, int userId, UserType userType);
    /**Javi da je projekt dodan u istaknute */
    void setAddHighlightListener(AddHighlightListener listener);
    /**javi da je projekt maknut iz istaknutih */
    void setRemoveHighlightListener(RemoveHighlightListener listener);

}
