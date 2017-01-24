package hr.foi.air.solex.models.projects;

public interface ProjectInteractor {
    void createNewProject(Project project);

    void setCreateListener(CreateProjectListener listener);

    void setListListener(ProjectListListener listListener);

    void getProjectList();

}
