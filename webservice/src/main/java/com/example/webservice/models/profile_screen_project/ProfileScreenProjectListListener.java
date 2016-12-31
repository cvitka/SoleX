package com.example.webservice.models.profile_screen_project;


import com.example.webservice.models.projects.ApiProject;

import java.util.List;

public interface ProfileScreenProjectListListener {
    void onProjectListCome(List<ProfileScreenProject> projects);
}
