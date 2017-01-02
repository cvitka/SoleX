package hr.foi.air.solex.activities.developers;


import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

public interface DeveloperProfileView {
    public void DataArrivedDeveloper(Developer developer);
    public void HighlihtedProjectsArrived(List<ProfileScreenProject> list);
    public void allSkillsListArrived(List<String> list);
    public void developerSkillsListArrived(List<String> list);
}
