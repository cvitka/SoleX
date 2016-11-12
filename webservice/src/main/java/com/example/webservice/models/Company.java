package com.example.webservice.models;

import java.sql.Blob;

/**
 * Created by corpus on 12.11.16..
 */

public class Company {

    int id;
    String name;
    String address;
    String email;
    String password;
    Blob picture;
    String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private static Company instance;

    public static Company getInstance() {
        if(instance==null)
            instance = new Company();
        return instance;
    }

    public Company() {
    }
}
