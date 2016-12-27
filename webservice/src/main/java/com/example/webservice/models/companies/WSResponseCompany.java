package com.example.webservice.models.companies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WSResponseCompany {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("naziv")
    @Expose
    private String naziv;

    @SerializedName("adresa")
    @Expose
    private String adresa;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("slika")
    @Expose
    private String  slika;

    @SerializedName("status")
    @Expose
    private String status;


    public WSResponseCompany() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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

    public String  getSlika() {
        return slika;
    }

    public void setSlika(String  slika) {
        this.slika = slika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
