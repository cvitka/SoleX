package hr.foi.air.solex.presenters.developers;

import java.util.List;

public interface ProjectSearchPreseneter {
    /**Dohvati sve kompetencije */
    public void getAllSkillList();
    /**Dohvati sve kompetencije za developera */
    public void getSkillList(int developerId);

}
