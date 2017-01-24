package hr.foi.air.solex.presenters.developers;


import java.sql.Blob;

public interface DeveloperProfilePresenter {
    public void getDeveloperData(int id);
    public void getHighlightedProjects(int developerId);
    public void getAllSkillList();
    public void getSkillList(int developerId);
    public void deleteSkill(int developerId, String skill);
    public void addSkill(int developerId, String skill);
}
