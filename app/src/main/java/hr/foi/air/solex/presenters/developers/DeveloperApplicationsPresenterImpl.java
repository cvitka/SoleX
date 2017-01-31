package hr.foi.air.solex.presenters.developers;


import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperApplicationsView;
import hr.foi.air.solex.models.collab_applicat.ApplicatListListener;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractor;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractorImpl;

public class DeveloperApplicationsPresenterImpl implements DeveloperApplicationsPresenter, ApplicatListListener {

    private DeveloperApplicationsView mApplicationsView;
    private CollabApplicatInteractor mCollabApplicatInteractor;
    public DeveloperApplicationsPresenterImpl(DeveloperApplicationsView applicationsView) {
        this.mApplicationsView = applicationsView;
        mCollabApplicatInteractor = new CollabApplicatInteractorImpl();
        mCollabApplicatInteractor.setApplicatListener(this);
    }

    /**Javi viewu da su apliciranja stigla*/
    @Override
    public void applicatListArrived(List<CollabApplicat> list) {
        mApplicationsView.onApplicationsArrived(list);
    }
    /**Javi interactoru da dohvati apliciranja*/
    @Override
    public void getApplications(int developerId) {
        mCollabApplicatInteractor.getApplications(developerId);
    }
}
