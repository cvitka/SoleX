package hr.foi.air.solex.presenters.companies;

public interface CompanyProfilePresenter {
    /**Dohvati podatke poduzeca */
    public void getCompany(int companyId);
    /** Dohvati istaknute projekte poduzeca*/
    public void getHighlightedProjects(int companyId);
    /**Dohvati sve kompetencije */
    public void getAllSkillList();
    /**Dohvati kompetencije za poduzece */
    public void getSkillList(int companyId);
    /**Izbrisi kompetenciju za poduzece */
    public void deleteSkill(int companyId, String skill);
    /** */
    /**Dodaj kompetenciju za poduzece */
    public void addSkill(int companyId, String skill);
}
