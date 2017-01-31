package hr.foi.air.solex.models.projects;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;


public interface ProjectInteractor {
    /**Kreiraj novi projekt*/
    void createNewProject(Project project);

    /**Javi da je kreiran novi projekt*/
    void setCreateListener(CreateProjectListener listener);

    void setListListener(ProjectListListener listListener);

    void getProjectList();
}
