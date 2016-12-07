package com.example.webservice.models.login_registration;

/**
 * Created by Asus on 6.12.2016..
 */

public interface LoginResponseListener {
    public void onLoginSuccessful(int id, String email, String type);
    public void onServerConnectionFailed();
    public void onLoginFailed(String message);
}
