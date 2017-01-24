package hr.foi.air.solex.presenters.companies;

public interface CompanyProfilePresenter {
    public void getCompany(int companyId);
    public void getHighlightedProjects(int companyId);
    public void getAllSkillList();
    public void getSkillList(int companyId);
    public void deleteSkill(int companyId, String skill);
    public void addSkill(int companyId, String skill);
}
