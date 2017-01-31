package hr.foi.air.solex.models.collab_applicat;

/** POJO klasa za kolboracije i aplikante*/
public class CollabApplicat {
    private String companyName;
    private int companyId;
    private String projectName;
    private String collaborationName;
    private int collaborationId;
    private int collaborationType;
    private String applicationDate;
    private String collaborationStartedDate;
    private String applicationState;
    private int pay;
    private int rating;
    private int applicatNumber;

    /** Definirani geteri i seteri*/
    public int getApplicatNumber() {
        return applicatNumber;
    }

    public void setApplicatNumber(int applicatNumber) {
        this.applicatNumber = applicatNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(String applicationState) {
        this.applicationState = applicationState;
    }

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

    public String getCollaborationStartedDate() {
        return collaborationStartedDate;
    }

    public void setCollaborationStartedDate(String collaborationStartedDate) {
        this.collaborationStartedDate = collaborationStartedDate;
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
