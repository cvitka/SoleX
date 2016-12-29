package com.example.webservice.models.companies;

import java.sql.Blob;
import java.util.HashMap;


public class Company {

    private int id;

    private String name;
    private String address;
    private String email;
    private String picture;
    private String status;
    private Blob pictureName;
    private HashMap<String, String> image;
    private String webStranica;
    private String direktor;
    private String opisPoduzeca;
    private int brojZaposlenika;

    public HashMap<String, String> getImage() {
        return image;
    }

    public String getDirektor() {
        return direktor;
    }

    public void setDirektor(String direktor) {
        this.direktor = direktor;
    }

    public String getOpisPoduzeca() {
        return opisPoduzeca;
    }

    public void setOpisPoduzeca(String opisPoduzeca) {
        this.opisPoduzeca = opisPoduzeca;
    }

    public int getBrojZaposlenika() {
        return brojZaposlenika;
    }

    public void setBrojZaposlenika(int brojZaposlenika) {
        this.brojZaposlenika = brojZaposlenika;
    }

    public String getWebStranica() {

        return webStranica;
    }

    public void setWebStranica(String webStranica) {
        this.webStranica = webStranica;
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
