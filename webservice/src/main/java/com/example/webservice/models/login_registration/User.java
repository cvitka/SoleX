package com.example.webservice.models.login_registration;

import com.example.core.utils.UserType;

/**
 * Created by cvitka on 11.11.16..
 */

public class User {
    int id;
    String email;
    UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static User instance;

    public static User getInstance() {
        if(instance==null)
            instance = new User();
        return instance;
    }

    private User() {
    }
}
