package hr.foi.air.solex.presenters;

import com.example.webservice.models.collaboration.AddNeededCollaborationListener;
import com.example.webservice.models.collaboration.NeededCollaboration;
import com.example.webservice.models.collaboration.NeededCollaborationInteractor;

import hr.foi.air.solex.activities.companies.NewNeededCollaborationView;

public class NewNeededCollaborationPresenterImpl implements NewNeededCollaborationPresenter, AddNeededCollaborationListener {

    private NewNeededCollaborationView mNewNeededCollabView;
    private NeededCollaborationInteractor mNewNeededCollabInteractor;

    public NewNeededCollaborationPresenterImpl(NewNeededCollaborationView mNewNeededCollabView, NeededCollaborationInteractor mNewNeededCollabInteractor) {
        this.mNewNeededCollabView = mNewNeededCollabView;
        this.mNewNeededCollabInteractor = mNewNeededCollabInteractor;
        mNewNeededCollabInteractor.setAddListener(this);
    }

    @Override
    public void addNeededCollaboration(NeededCollaboration neededCollaboration) {
        mNewNeededCollabInteractor.addNeededCollaboration(neededCollaboration);

    }

    @Override
    public void onNeededCollaborationAdd() {
        mNewNeededCollabView.onAdd();

    }
}
