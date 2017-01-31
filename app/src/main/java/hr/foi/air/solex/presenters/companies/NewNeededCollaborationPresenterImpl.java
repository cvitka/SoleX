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

    /**Javi interactoru da dohvati potrebne suradnje */
    @Override
    public void addNeededCollaboration(NeededCollaboration neededCollaboration) {
        mNewNeededCollabInteractor.addNeededCollaboration(neededCollaboration);

    }

    /**Javi viewu da je suradnje dodana*/
    @Override
    public void onNeededCollaborationAdd() {
        mNewNeededCollabView.onAdd();

    }

    /**Javi interactoru da dohvati potrebne kompetencije*/
    @Override
    public void getAllSkills() {
        mSkillsInteractor.getAllSkillList();
    }

    /**Javi viewu da su kompetencije stigle*/
    @Override
    public void onAllSkillListCome(List<String> skills) {
        mNewNeededCollabView.onAllSkillsArrived(skills);
    }
}
