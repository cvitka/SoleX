package hr.foi.air.solex.presenters.companies;

public interface ProjectManagementPresenter {
    /**Dohvati projekt */
    public void getProject(int id);
    /**Dohvati potrebne suradnje */
    void getNeededCollaboration(int id);
}
