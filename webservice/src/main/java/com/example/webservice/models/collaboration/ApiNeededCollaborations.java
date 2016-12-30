package com.example.webservice.models.collaboration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiNeededCollaborations {

    @SerializedName("potrebneSuradnjeId")
    @Expose
    private String collabId;

    @SerializedName("nazivSuradnje")
    @Expose
    private String devNcollabNme;

    public String getCollabId() {
        return collabId;
    }

    public void setCollabId(String collabId) {
        this.collabId = collabId;
    }

    public String getDevNcollabNme() {
        return devNcollabNme;
    }

    public void setDevNcollabNme(String devNcollabNme) {
        this.devNcollabNme = devNcollabNme;
    }
}
