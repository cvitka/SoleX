package com.example.webservice.models.projects;

import com.example.webservice.models.companies.Company;

import java.util.List;

public interface ProjectInteractor {
    void createNewProject(Project project);

    void setCreateListener(CreateProjectListener listener);

    void setListListener(ProjectListListener listListener);

    void getProjectList();

}
