package hr.foi.air.solex.presenters.companies;


import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyNeededCollaborationView;
import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.models.applicants.ApplicantInteractor;
import hr.foi.air.solex.models.applicants.ApplicantInteractorImpl;
import hr.foi.air.solex.models.applicants.ApplicantListListener;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;
import hr.foi.air.solex.models.collaboration.NeededCollaborationDataScalarListener;
import hr.foi.air.solex.models.collaboration.PushNotificationListenerCompany;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

public class CompanyNeededCollaborationPresenterImpl implements CompanyNeededCollaborationPresenter, ApplicantListListener, SkillListListener, NeededCollaborationDataScalarListener, ApplicationAcceptedListener,PushNotificationListenerCompany {
    private CompanyNeededCollaborationView mCompanyNeededCollabView;
    private ApplicantInteractor mApplicantInteractor;
    private SkillsInteractor mSkillsInteractor;
    private ApiNeededCollaborationsInteractor mApiCompanyCollaborationsInteractor;

    public CompanyNeededCollaborationPresenterImpl(CompanyNeededCollaborationView companyNeededCollabView) {
        this.mCompanyNeededCollabView = companyNeededCollabView;
        mApplicantInteractor = new ApplicantInteractorImpl(this);
        mSkillsInteractor = new SkillsInteractorImpl();
        mSkillsInteractor.setSkillListListener(this);
        mApiCompanyCollaborationsInteractor = new ApiNeededCollaborationsInteractorImpl();
        mApiCompanyCollaborationsInteractor.setNeededCollabDataListener(this);
        mApiCompanyCollaborationsInteractor.setApplicationAcceptedListener(this);
        mApiCompanyCollaborationsInteractor.setPushNotificationListenerCompany(this);
    }


    @Override
    public void getApplicants(int collaborationId) {
        mApplicantInteractor.getApplicantList(collaborationId);
    }

    @Override
    public void getCollaborationSkills(int collaborationId) {
        mSkillsInteractor.getCollaborationSkillList(collaborationId);
    }

    @Override
    public void getCollaborationData(int collaborationId) {
        mApiCompanyCollaborationsInteractor.getNeededCollaboration(collaborationId);
    }

    @Override
    public void pushNotification(int developerId) {
        mApiCompanyCollaborationsInteractor.pushNotificationDeveloper(developerId);
    }

    @Override
    public void onApplicantListCome(List<Applicant> applicants) {
        mCompanyNeededCollabView.onApplicantsArrived(applicants);
    }

    @Override
    public void onSkillListCome(List<String> skills) {
        mCompanyNeededCollabView.onSkillsListArrived(skills);
    }

    @Override
    public void applicantChosen(int collaborationId, int applicantId) {
        mApiCompanyCollaborationsInteractor.applicationAccepted(collaborationId, applicantId);
    }

    @Override
    public void neededCollaborationDataArrived(NeededCollaborationData collaborationData) {
        mCompanyNeededCollabView.onCollaborationDataArrived(collaborationData);
    }

    @Override
    public void onSuccessfulAssign() {
        mCompanyNeededCollabView.onSuccessfullAssign();
    }

    @Override
    public void notificationPushedDeveloper() {
        mCompanyNeededCollabView.onPushSuccessful();
    }
}
