package com.example.webservice.models;

import java.sql.Blob;

/**
 * Created by cvitka on 11.11.16..
 */

public class Developer {
    int id;
    String ime;
    String prezime;
    String adresa;
    String email;
    String kontaktBroj;
    String password;
    int godineIskustva;
    Blob slika;
    String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKontaktBroj() {
        return kontaktBroj;
    }

    public void setKontaktBroj(String kontaktBroj) {
        this.kontaktBroj = kontaktBroj;
    }

    public int getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(int godineIskustva) {
        this.godineIskustva = godineIskustva;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getSlika() {
        return slika;
    }

    public void setSlika(Blob slika) {
        this.slika = slika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private static Developer instance;

    public static Developer getInstance() {
        if(instance==null)
            instance = new Developer();
        return instance;
    }

    public Developer() {
    }
}
