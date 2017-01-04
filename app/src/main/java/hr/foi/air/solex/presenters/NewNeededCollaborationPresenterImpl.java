package hr.foi.air.solex.presenters;

import com.example.webservice.models.collaboration.AddNeededCollaborationListener;
import com.example.webservice.models.collaboration.NeededCollaboration;
import com.example.webservice.models.collaboration.NeededCollaborationInteractor;
import com.example.webservice.models.skills.AllSkillListListener;
import com.example.webservice.models.skills.SkillsInteractor;
import com.example.webservice.models.skills.SkillsInteractorImpl;

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
