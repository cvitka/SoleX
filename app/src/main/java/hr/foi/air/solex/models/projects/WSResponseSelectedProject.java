package hr.foi.air.solex.models.projects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class WSResponseSelectedProject {

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

    @SerializedName("statusProjektaId")
    @Expose
    private int statusProjektaId;

    @SerializedName("opisProjekta")
    @Expose
    private String opisProjekta;

    @SerializedName("trajanjeOD")
    @Expose
    private Date trajanjeOD;

    @SerializedName("trajanjeDO")
    @Expose
    private Date trajanjeDO;

    public WSResponseSelectedProject() {
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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getStatusProjektaId() {
        return statusProjektaId;
    }

    public void setStatusProjektaId(int statusProjektaId) {
        this.statusProjektaId = statusProjektaId;
    }

    public String getOpisProjekta() {
        return opisProjekta;
    }

    public void setOpisProjekta(String opisProjekta) {
        this.opisProjekta = opisProjekta;
    }

    public Date getTrajanjeOD() {
        return trajanjeOD;
    }

    public void setTrajanjeOD(Date trajanjeOD) {
        this.trajanjeOD = trajanjeOD;
    }

    public Date getTrajanjeDO() {
        return trajanjeDO;
    }

    public void setTrajanjeDO(Date trajanjeDO) {
        this.trajanjeDO = trajanjeDO;
    }
}
