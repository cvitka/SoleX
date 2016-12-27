package com.example.webservice.models.projects;

public interface ProjectInteractor {
    void createNewProject(Project project);
    void setCreateListener(CreateProjectListener listener);
}
