package hr.foi.air.solex.models.projects;

import java.util.List;

/**Listener za listu projekata */
public interface ProjectListListener {
    void onProjectListCome(List<ApiProject> projects);
}
