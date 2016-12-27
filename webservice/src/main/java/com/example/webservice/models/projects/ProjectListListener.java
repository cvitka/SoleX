package com.example.webservice.models.projects;

import java.util.List;

public interface ProjectListListener {
    void onProjectListCome(List<ApiProject> projects);
}
