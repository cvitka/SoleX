package hr.foi.air.solex.presenters;

import com.example.webservice.models.projects.CreateProjectListener;
import com.example.webservice.models.projects.Project;
import com.example.webservice.models.projects.ProjectInteractor;
import com.example.webservice.models.projects.ProjectInteractorImpl;


import java.util.Date;

import hr.foi.air.solex.activities.companies.AddNewProjectView;

public class AddNewProjectPresenterImpl implements AddNewProjectPresenter, CreateProjectListener {
    private AddNewProjectView mAddNewProjectView;
    private ProjectInteractor mProjectInteractor;

    public AddNewProjectPresenterImpl(AddNewProjectView newProjectView, ProjectInteractor projectInteractor) {
        mAddNewProjectView = newProjectView;
        mProjectInteractor = projectInteractor;
        mProjectInteractor.setCreateListener(this);
    }

    @Override
    public void createNewProject(Project project) {
        mProjectInteractor.createNewProject(project);
    }

    @Override
    public void onProjectCreate() {
        mAddNewProjectView.onCreation();
    }
}
