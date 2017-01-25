package hr.foi.air.solex.models.collab_applicat;


import java.sql.Date;

public class CollabApplicat {
    private String companyName;
    private String projectName;
    private String collaborationName;
    private int collaborationType;
    private String applicationDate;
    private int pay;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

    public int getCollaborationType() {
        return collaborationType;
    }

    public void setCollaborationType(int collaborationType) {
        this.collaborationType = collaborationType;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
