package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationListListener;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.projects.SelectedProjectInteractor;
import hr.foi.air.solex.models.projects.SelectedProjectListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.ProjectManagementView;

public class ProjectManagementPresenterImpl implements ProjectManagementPresenter, SelectedProjectListener, ApiNeededCollaborationListListener {

    ProjectManagementView mProjectManagementView;
    SelectedProjectInteractor mInteractor;
    ApiNeededCollaborationsInteractor mCollaborationsInteractor;

    public ProjectManagementPresenterImpl(ProjectManagementView mProjectManagementView, SelectedProjectInteractor mInteractor) {
        this.mProjectManagementView = mProjectManagementView;
        this.mInteractor = mInteractor;
        mCollaborationsInteractor = new ApiNeededCollaborationsInteractorImpl();
        mCollaborationsInteractor.setListListener(this);
        mInteractor.setScalarListener(this);
    }

    /**Javi interactoru da dohvati projekt*/
    @Override
    public void getProject(int id) {
        mInteractor.getSelectedProjectData(id);
    }

    /**Javi viewu da je projekt stigao*/
    @Override
    public void onDataCome(Project project) {
        mProjectManagementView.DataArrived(project);
    }

    /**Javi interactoru da dohvati potrebne suradnje*/
    @Override
    public void getNeededCollaboration(int id) {
        mCollaborationsInteractor.getData(id);
    }

    /**Javi viewu da da su suradnje stigle*/
    @Override
    public void onDataListCome(List<ApiNeededCollaborations> neededCollaborationses) {
        mProjectManagementView.NeededCollaborationsArrived(neededCollaborationses);
    }
}
