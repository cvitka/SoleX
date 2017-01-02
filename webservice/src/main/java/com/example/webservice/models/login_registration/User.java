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

    //checks if the user is current one
    public static boolean isCurrentUser(UserType userType, int id){
        //type and id must match
        return getInstance().getUserType().intVal()==userType.intVal()&&getInstance().getId()==id;
    }

    public static boolean isCompany(){
        return getInstance().getUserType().intVal()==UserType.COMPANY.intVal();
    }

    public static boolean isDeveloper(){
        return getInstance().getUserType().intVal()==UserType.DEVELOPER.intVal();
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
