package com.example.webservice.models.developers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResponseDeveloper {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("ime")
    @Expose
    private String ime;

    @SerializedName("prezime")
    @Expose
    private String prezime;

    @SerializedName("adresa")
    @Expose
    private String adresa;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("kontaktBroj")
    @Expose
    private String kontaktBroj;

    @SerializedName("godineIskustva")
    @Expose
    private String godineIskustva;

    @SerializedName("slika")
    @Expose
    private String slika;

    @SerializedName("status")
    @Expose
    private String status;

    public WSResponseDeveloper() {
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

    public String getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(String godineIskustva) {
        this.godineIskustva = godineIskustva;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
