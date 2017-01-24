package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.collaboration.AddNeededCollaborationListener;
import hr.foi.air.solex.models.collaboration.NeededCollaboration;
import hr.foi.air.solex.models.collaboration.NeededCollaborationInteractor;
import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

import java.util.List;

import hr.foi.air.solex.activities.companies.NewNeededCollaborationView;

public class NewNeededCollaborationPresenterImpl implements NewNeededCollaborationPresenter, AddNeededCollaborationListener, AllSkillListListener {

    private NewNeededCollaborationView mNewNeededCollabView;
    private NeededCollaborationInteractor mNewNeededCollabInteractor;
    private SkillsInteractor mSkillsInteractor;

    public NewNeededCollaborationPresenterImpl(NewNeededCollaborationView mNewNeededCollabView, NeededCollaborationInteractor mNewNeededCollabInteractor) {
        this.mNewNeededCollabView = mNewNeededCollabView;
        this.mNewNeededCollabInteractor = mNewNeededCollabInteractor;
        mNewNeededCollabInteractor.setAddListener(this);
        mSkillsInteractor = new SkillsInteractorImpl();
        mSkillsInteractor.setAllSkillListListener(this);

    }

    @Override
    public void addNeededCollaboration(NeededCollaboration neededCollaboration) {
        mNewNeededCollabInteractor.addNeededCollaboration(neededCollaboration);

    }

    @Override
    public void onNeededCollaborationAdd() {
        mNewNeededCollabView.onAdd();

    }

    @Override
    public void getAllSkills() {
        mSkillsInteractor.getAllSkillList();
    }

    @Override
    public void onAllSkillListCome(List<String> skills) {
        mNewNeededCollabView.onAllSkillsArrived(skills);
    }
}
