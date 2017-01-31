package hr.foi.air.solex.presenters.common;


import hr.foi.air.solex.utils.UserType;

public interface ProjectListingPresenter {
    /**Dohvati projekte za korisnika */
    void getProjects(int id, UserType userType);
}
