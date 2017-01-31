package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.models.mdevelopers.Developer;


public interface DeveloperSignupPresenter {
    /**Izvrsi registraciju */
    public void tryRegister(Developer developer, String password);
}
