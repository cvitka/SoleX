package hr.foi.air.solex.presenters.developers;


import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperApplicationsView;
import hr.foi.air.solex.activities.developers.DeveloperCollaborationsView;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractor;
import hr.foi.air.solex.models.collab_applicat.CollabApplicatInteractorImpl;
import hr.foi.air.solex.models.collab_applicat.CollabListListener;
import hr.foi.air.solex.models.ratings.RatingsInteractor;
import hr.foi.air.solex.models.ratings.RatingsInteractorImpl;
import hr.foi.air.solex.models.ratings.RatingsListener;

public class DeveloperCollaborationsPresenterImpl implements DeveloperCollaborationsPresenter, CollabListListener, RatingsListener {
    private DeveloperCollaborationsView mCollaborationsView;
    private CollabApplicatInteractor mCollabApplicatInteractor;
    private RatingsInteractor mRatingInteractor;
    public DeveloperCollaborationsPresenterImpl(DeveloperCollaborationsView collaborationsView) {
        this.mCollaborationsView = collaborationsView;
        mCollabApplicatInteractor = new CollabApplicatInteractorImpl();
        mCollabApplicatInteractor.setCollabListener(this);
        mRatingInteractor = new RatingsInteractorImpl(this);
    }

    /**Javi interactoru da dohvati suradnje*/
    @Override
    public void getCollaborations(int developerId) {
        mCollabApplicatInteractor.getCollaborations(developerId);
    }

    /**Javi interactoru da ocijeni*/
    @Override
    public void rate(int rating, int user, int collaborationId) {
        mRatingInteractor.rate(rating, user, collaborationId);
    }

    /**Javi viewu da su suradnje stigle*/
    @Override
    public void collabListArrived(List<CollabApplicat> list) {
        mCollaborationsView.onCollaborationsArrived(list);

    }

    /**Javi viewu da je osjenjeno*/
    @Override
    public void onRatingSucceeded() {
        mCollaborationsView.onRateSucceeded();
    }
}
