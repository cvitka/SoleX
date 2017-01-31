package hr.foi.air.solex.models.signup;

import hr.foi.air.solex.models.mdevelopers.Developer;

/**Registriraj developera */
public interface DeveloperSignupInteractor {
    public void registerDeveloper(Developer developer, String password);
}
