package hr.foi.air.solex.presenters;

import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationListListener;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborations;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;

import java.util.List;

import hr.foi.air.solex.activities.companies.ProjectManagementView;

public class GetNeededCollaborationsPresenterImpl implements  GetNeededCollaaborationsPresenter, ApiNeededCollaborationListListener{

    ProjectManagementView mView;
    ApiNeededCollaborationsInteractor mInteractor;

    public GetNeededCollaborationsPresenterImpl(ProjectManagementView mView, ApiNeededCollaborationsInteractor mInteractor) {
        this.mView = mView;
        this.mInteractor = mInteractor;
        mInteractor.setListListener(this);
    }

    @Override
    public void getNeededCollaboration(int id) {
        mInteractor.getData(id);

    }

    @Override
    public void onDataListCome(List<ApiNeededCollaborations> neededCollaborationses) {
        mView.NeededCollaborationsArrived(neededCollaborationses);
    }
}
