package com.example.webservice;

import com.example.core.utils.LoginStatus;
import com.example.webservice.models.User;

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
