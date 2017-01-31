package hr.foi.air.solex.models.collaboration;

/**POJO klasa za potrebne suradnje  */
public class NeededCollaborationData {
    private String projectName;
    private int companyId;
    private String collaborationName;
    private String collaborationDescription;
    private int collaborationId;
    private int state;

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

    public String getCollaborationDescription() {
        return collaborationDescription;
    }

    public void setCollaborationDescription(String collaborationDescription) {
        this.collaborationDescription = collaborationDescription;
    }

    public int getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
