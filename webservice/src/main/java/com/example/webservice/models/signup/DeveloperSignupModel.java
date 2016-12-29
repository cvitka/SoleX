package com.example.webservice.models.signup;

import com.example.webservice.models.mdevelopers.Developer;

/**
 * Created by Asus on 6.12.2016..
 */

public interface DeveloperSignupModel {
    public void registerDeveloper(Developer developer, String password);
}
