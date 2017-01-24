package hr.foi.air.solex.models.projects;

import java.util.List;

public interface ProjectListListener {
    void onProjectListCome(List<ApiProject> projects);
}
