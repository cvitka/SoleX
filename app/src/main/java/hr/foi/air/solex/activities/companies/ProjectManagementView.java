package hr.foi.air.solex.activities.companies;

import com.example.webservice.models.collaboration.ApiNeededCollaborations;
import com.example.webservice.models.projects.Project;

import java.util.List;

public interface ProjectManagementView {
    void DataArrived(Project project);
    void NeededCollaborationsArrived(List<ApiNeededCollaborations> neededCollaborationses);
}
