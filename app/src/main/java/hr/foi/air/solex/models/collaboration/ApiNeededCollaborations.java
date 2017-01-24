package hr.foi.air.solex.models.collaboration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiNeededCollaborations {

    @SerializedName("potrebneSuradnjeId")
    @Expose
    private int collabId;

    @SerializedName("nazivSuradnje")
    @Expose
    private String devNcollabNme;

    @SerializedName("hasCollaborator")
    @Expose
    private int hasCollaborator;

    public int getHasCollaborator() {
        return hasCollaborator;
    }

    public void setHasCollaborator(int hasCollaborator) {
        this.hasCollaborator = hasCollaborator;
    }

    public int getCollabId() {
        return collabId;
    }

    public void setCollabId(int collabId) {
        this.collabId = collabId;
    }

    public String getDevNcollabNme() {
        return devNcollabNme;
    }

    public void setDevNcollabNme(String devNcollabNme) {
        this.devNcollabNme = devNcollabNme;
    }
}
