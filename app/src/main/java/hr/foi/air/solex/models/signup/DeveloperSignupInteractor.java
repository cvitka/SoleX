package hr.foi.air.solex.models.signup;

import hr.foi.air.solex.models.mdevelopers.Developer;

/**
 * Created by Asus on 6.12.2016..
 */

public interface DeveloperSignupInteractor {
    public void registerDeveloper(Developer developer, String password);
}
