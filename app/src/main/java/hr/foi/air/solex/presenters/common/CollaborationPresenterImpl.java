package hr.foi.air.solex.presenters.common;


import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationScalarListener;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

import java.util.List;

import hr.foi.air.solex.activities.common.CollaborationView;
import hr.foi.air.solex.presenters.common.CollaborationPresenter;

public class CollaborationPresenterImpl implements CollaborationPresenter, SkillListListener, ApiCompanyCollaborationScalarListener {

    private CollaborationView mCollaborationView;
    private SkillsInteractor mSkillsInteractor;
    private ApiCompanyCollaborationsInteractor mApyCompanyCollaborationsInteractor;

    public CollaborationPresenterImpl(CollaborationView collabView) {
        /**Postavljanje viewa, interactora  i listenera */
        this.mCollaborationView = collabView;
        mSkillsInteractor = new SkillsInteractorImpl();
        mSkillsInteractor.setSkillListListener(this);
        mApyCompanyCollaborationsInteractor = new ApiCompanyCollaborationsInteractorImpl();
        mApyCompanyCollaborationsInteractor.setScalarListener(this);
    }

    /**Trazi postojece suradnje od interactora */
    @Override
    public void getExistingCollaboration(int collaborationId) {
        mApyCompanyCollaborationsInteractor.getCollaborationScalar(collaborationId);
    }

    /**Trazi kompetencije od interactora */
    @Override
    public void getSkillsForCollaboration(int collaborationId) {
        mSkillsInteractor.getCollaborationSkillList(collaborationId);
    }

    /**Javi viewu da su kompetencije stigle */
    @Override
    public void onSkillListCome(List<String> skills) {
        mCollaborationView.onSkillsArrived(skills);
    }

    /**Javi viewu da su suradnje stigle */
    @Override
    public void companyCollaborationArrived(ApiCompanyCollaborations collaboration) {
        mCollaborationView.onCollaborationArrived(collaboration);
    }
}
