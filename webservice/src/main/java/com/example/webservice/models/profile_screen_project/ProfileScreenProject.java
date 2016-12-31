package com.example.webservice.models.profile_screen_project;

public class ProfileScreenProject {
    private int projectId;
    private String projectName;
    private String companyName;
    private int state;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
