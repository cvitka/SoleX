package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.example.webservice.models.projects.ApiProject;
import com.example.webservice.models.projects.Project;

import java.util.List;

public interface CompanyProjectsView {
    void onDataArrived(List<ProfileScreenProject> projects);
}
