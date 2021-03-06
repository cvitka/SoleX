package hr.foi.air.solex.models.projects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**POJO za listu projekata */
public class ApiProject {
    @SerializedName("projektiId")
    @Expose
    private String projektiId;

    @SerializedName("naziv")
    @Expose
    private String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProjektiId() {
        return projektiId;
    }

    public void setProjektiId(String projektiId) {
        this.projektiId = projektiId;
    }
}
