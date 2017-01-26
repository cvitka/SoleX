package hr.foi.air.solex.presenters.developers;


import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyNeededCollaborationView;
import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationView;
import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.models.applicants.ApplicantInteractor;
import hr.foi.air.solex.models.applicants.ApplicantInteractorImpl;
import hr.foi.air.solex.models.applicants.ApplicantListListener;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import hr.foi.air.solex.models.collaboration.DeveloperAppliesListener;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;
import hr.foi.air.solex.models.collaboration.NeededCollaborationDataScalarListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;
import hr.foi.air.solex.presenters.companies.ApplicationAcceptedListener;
import hr.foi.air.solex.presenters.companies.CompanyNeededCollaborationPresenter;

public class DeveloperNeededCollaborationPresenterImpl implements DeveloperNeededCollaborationPresenter, SkillListListener, NeededCollaborationDataScalarListener, DeveloperAppliesListener {
    private DeveloperNeededCollaborationView mDeveloperNeededCollaborationView;
    private SkillsInteractor mSkillsInteractor;
    private ApiNeededCollaborationsInteractor mApiCompanyCollaborationsInteractor;

    public DeveloperNeededCollaborationPresenterImpl(DeveloperNeededCollaborationView developerNeededCollaborationView) {
        this.mDeveloperNeededCollaborationView = developerNeededCollaborationView;
        mSkillsInteractor = new SkillsInteractorImpl();
        mSkillsInteractor.setSkillListListener(this);
        mApiCompanyCollaborationsInteractor = new ApiNeededCollaborationsInteractorImpl();
        mApiCompanyCollaborationsInteractor.setNeededCollabDataListener(this);
        mApiCompanyCollaborationsInteractor.setDeveloperAppliesListener(this);
    }


    @Override
    public void getCollaborationSkills(int collaborationId) {
        mSkillsInteractor.getCollaborationSkillList(collaborationId);
    }

    @Override
    public void getCollaborationData(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.getNeededCollaboration(collaborationId, developerId);
    }

    @Override
    public void onSkillListCome(List<String> skills) {
        mDeveloperNeededCollaborationView.onSkillsArrived(skills);
    }

    @Override
    public void neededCollaborationDataArrived(NeededCollaborationData collaborationData) {
        mDeveloperNeededCollaborationView.onCollaborationDataArrived(collaborationData);
    }

    @Override
    public void onApplySuccessfull() {
        mDeveloperNeededCollaborationView.onApplySuccessfull();
    }

    @Override
    public void onApplyRemoveSuccessfull() {
        mDeveloperNeededCollaborationView.onRemoveApplySucessfull();
    }

    @Override
    public void apply(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.developerApplied(collaborationId, developerId);
    }

    @Override
    public void removeApply(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.developerRemovedApply(collaborationId, developerId);
    }
}
