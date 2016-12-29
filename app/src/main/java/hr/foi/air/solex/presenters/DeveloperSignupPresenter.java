package hr.foi.air.solex.presenters;

import com.example.webservice.models.mdevelopers.Developer;

/**
 * Created by Asus on 7.12.2016..
 */

public interface DeveloperSignupPresenter {
    public void tryRegister(Developer developer, String password);
}
