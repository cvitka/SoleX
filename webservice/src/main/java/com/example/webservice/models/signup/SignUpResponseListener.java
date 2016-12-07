package com.example.webservice.models.signup;

/**
 * Created by Asus on 6.12.2016..
 */

public interface SignupResponseListener {
    public void onRegistrationSuccessful();
    public void onServerConnectionFailed();
    public void onRegistrationFailed(String message);
}
