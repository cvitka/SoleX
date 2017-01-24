package hr.foi.air.solex.models.profile_screen_project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResponseProfileScreenProject {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("projectId")
    @Expose
    private int projectId;

    @SerializedName("projectName")
    @Expose
    private String projectName;

    @SerializedName("companyName")
    @Expose
    private String companyName;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("numOfCollaborations")
    @Expose
    private int numOfCollaborations;

    public int getNumOfCollaborations() {
        return numOfCollaborations;
    }

    public void setNumOfCollaborations(int numOfCollaborations) {
        this.numOfCollaborations = numOfCollaborations;
    }

    public int getId() {
        return projectId;
    }

    public void setId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyId) {
        this.companyName = companyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
