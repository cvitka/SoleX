package hr.foi.air.solex.presenters.common;


import java.util.List;

import hr.foi.air.solex.activities.common.ProjectDisplayView;
import hr.foi.air.solex.activities.companies.ProjectManagementView;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationListListener;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.SelectedProjectInteractor;
import hr.foi.air.solex.models.projects.SelectedProjectListener;

public class ProjectDisplayPresenterImpl implements ProjectDisplayPresenter, SelectedProjectListener, ApiNeededCollaborationListListener {

    private ProjectDisplayView mProjectDisplayView;
    private SelectedProjectInteractor mInteractor;
    private ApiNeededCollaborationsInteractor mCollaborationsInteractor;

    public ProjectDisplayPresenterImpl(ProjectDisplayView projectDisplayView, SelectedProjectInteractor mInteractor) {
        this.mProjectDisplayView = projectDisplayView;
        this.mInteractor = mInteractor;
        mCollaborationsInteractor = new ApiNeededCollaborationsInteractorImpl();
        mCollaborationsInteractor.setListListener(this);
        mInteractor.setScalarListener(this);
    }

    @Override
    public void getProject(int id) {
        mInteractor.getSelectedProjectData(id);
    }

    @Override
    public void onDataCome(Project project) {
        mProjectDisplayView.DataArrived(project);
    }

    @Override
    public void getNeededCollaboration(int id) {
        mCollaborationsInteractor.getData(id);
    }

    @Override
    public void onDataListCome(List<ApiNeededCollaborations> neededCollaborationses) {
        mProjectDisplayView.NeededCollaborationsArrived(neededCollaborationses);
    }
}