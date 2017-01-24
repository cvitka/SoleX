package hr.foi.air.solex.activities.common;


import java.util.List;

import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.projects.Project;

public interface ProjectDisplayView {
    void DataArrived(Project project);
    void NeededCollaborationsArrived(List<ApiNeededCollaborations> neededCollaborationses);
}
