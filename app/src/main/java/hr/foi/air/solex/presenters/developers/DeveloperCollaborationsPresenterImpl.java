package hr.foi.air.solex.presenters.developers;


import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperApplicationsView;
import hr.foi.air.solex.activities.developers.DeveloperCollaborationsView;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractor;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractorImpl;
import hr.foi.air.solex.models.collab_applicat.CollabListListener;

public class DeveloperCollaborationsPresenterImpl implements DeveloperCollaborationsPresenter, CollabListListener {
    private DeveloperCollaborationsView mCollaborationsView;
    private CollabApplicatInteractor mCollabApplicatInteractor;
    public DeveloperCollaborationsPresenterImpl(DeveloperCollaborationsView collaborationsView) {
        this.mCollaborationsView = collaborationsView;
        mCollabApplicatInteractor = new CollabApplicatInteractorImpl();
        mCollabApplicatInteractor.setCollabListener(this);
    }

    @Override
    public void getCollaborations(int developerId) {
        mCollabApplicatInteractor.getCollaborations(developerId);
    }

    @Override
    public void collabListArrived(List<CollabApplicat> list) {
        mCollaborationsView.onCollaborationsArrived(list);

    }
}
