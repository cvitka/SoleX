package hr.foi.air.solex.models.mdevelopers;

/**
 * Created by cvitka on 11.11.16..
 */

public class Developer {
    private int id;
    private String ime;
    private String prezime;
    private String adresa;
    private String email;
    private String kontaktBroj;
    private String godineIskustva;
    private String picture;
    private String status;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
