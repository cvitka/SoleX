package com.example.webservice.models.companies;

import java.sql.Blob;
import java.util.HashMap;

/**
 * Created by corpus on 12.11.16..
 */

public class Company {

    private int id;
    private String name;
    private String address;
    private String email;
    private String picture;
    private String status;
    private Blob pictureName;
    private HashMap<String, String> image;

    public HashMap<String, String> getImage() {
        return image;
    }

    public void setImage(HashMap<String, String> image) {
        this.image = image;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Blob getPictureName() {
        return pictureName;
    }

    public void setPictureName(Blob pictureName) {
        this.pictureName = pictureName;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private static Company instance;

    public Company() {
    }
}
