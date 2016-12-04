package com.example.webservice.models.login_registration;

import com.example.core.utils.LoginStatus;

/**
 * Created by cvitka on 11.11.16..
 */

public interface WebServiceHandler {
    void onLogin();
    void onRegistration();
    void onRegistrationCompany();
    void onFailedLogin(LoginStatus loginStatus);
    void onFailedRegistration(LoginStatus loginStatus);
    void onFailedRegistrationCompany(LoginStatus loginStatus);
}
