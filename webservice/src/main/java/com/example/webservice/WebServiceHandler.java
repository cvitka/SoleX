package com.example.webservice;

import com.example.webservice.models.User;

/**
 * Created by cvitka on 11.11.16..
 */

public interface WebServiceHandler {
    void onLogin(User user);
}
