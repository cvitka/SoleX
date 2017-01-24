package hr.foi.air.solex.activities.companies;

import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.projects.Project;

import java.util.List;

public interface ProjectManagementView {
    void DataArrived(Project project);
    void NeededCollaborationsArrived(List<ApiNeededCollaborations> neededCollaborationses);
}
