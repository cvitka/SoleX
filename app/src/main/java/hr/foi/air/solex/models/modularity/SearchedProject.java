package hr.foi.air.solex.models.modularity;


/**Klasa trazeni projekt */
public class SearchedProject {
    //
    private String projectName;
    private String companyName;
    private int collaborationId;
    private String collaborationName;
    private int applicantsNum;
    private int matches;
    private int numOfNeededSkills;

    public int getNumOfNeededSkills() {
        return numOfNeededSkills;
    }

    public void setNumOfNeededSkills(int numOfNeededSkills) {
        this.numOfNeededSkills = numOfNeededSkills;
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

    public String getComapanyName() {
        return companyName;
    }

    public void setComapanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

    public int getApplicantsNum() {
        return applicantsNum;
    }

    public void setApplicantsNum(int applicantsNum) {
        this.applicantsNum = applicantsNum;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }
}
