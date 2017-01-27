package hr.foi.air.solex.models.projects;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public interface ProjectInteractor {
    void createNewProject(Project project);

    void setCreateListener(CreateProjectListener listener);

    void setListListener(ProjectListListener listListener);

    void getProjectList();
}
