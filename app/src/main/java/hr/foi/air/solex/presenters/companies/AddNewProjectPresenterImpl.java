package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.projects.CreateProjectListener;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.ProjectInteractor;


import hr.foi.air.solex.activities.companies.AddNewProjectView;

public class AddNewProjectPresenterImpl implements AddNewProjectPresenter, CreateProjectListener {

    private AddNewProjectView mAddNewProjectView;
    private ProjectInteractor mProjectInteractor;

    public AddNewProjectPresenterImpl(AddNewProjectView newProjectView, ProjectInteractor projectInteractor) {
        mAddNewProjectView = newProjectView;
        mProjectInteractor = projectInteractor;
        mProjectInteractor.setCreateListener(this);
    }

    /**javi interactoru da kreira projekt */
    @Override
    public void createNewProject(Project project) {
        mProjectInteractor.createNewProject(project);
    }

    /**javi viewu da je projekt kreiran */
    @Override
    public void onProjectCreate(int newProjectId) {
        mAddNewProjectView.onCreation(newProjectId);
    }
}
