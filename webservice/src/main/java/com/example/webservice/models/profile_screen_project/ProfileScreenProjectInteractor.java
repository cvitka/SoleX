package com.example.webservice.models.profile_screen_project;

import com.example.webservice.models.projects.CreateProjectListener;
import com.example.webservice.models.projects.Project;
import com.example.webservice.models.projects.ProjectListListener;

public interface ProfileScreenProjectInteractor {
    void getHighlightedProjectList(int companyId);
}
