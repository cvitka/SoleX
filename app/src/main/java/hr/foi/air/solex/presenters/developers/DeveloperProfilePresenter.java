package hr.foi.air.solex.presenters.developers;


import java.sql.Blob;

public interface DeveloperProfilePresenter {
    /**Dohvati podatke developera */
    public void getDeveloperData(int id);
    /**Dohvati istaknute projekte */
    public void getHighlightedProjects(int developerId);
    /**Dohvati sve kompetencije */
    public void getAllSkillList();
    /**Dohvati sve kompetencije za developera */
    public void getSkillList(int developerId);
    /** Ukloni kompetenciju*/
    public void deleteSkill(int developerId, String skill);
    /**Dodaj kompetenciju */
    public void addSkill(int developerId, String skill);
}
