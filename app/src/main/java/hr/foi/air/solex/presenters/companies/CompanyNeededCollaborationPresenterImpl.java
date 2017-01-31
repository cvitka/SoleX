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


    /**Javi interactoru da dohvati aplikante */
    @Override
    public void getApplicants(int collaborationId) {
        mApplicantInteractor.getApplicantList(collaborationId);
    }

    /**Javi interactoru da dohvati suradnje*/
    @Override
    public void getCollaborationSkills(int collaborationId) {
        mSkillsInteractor.getCollaborationSkillList(collaborationId);
    }

    /**Javi interactoru da dohvati podatke o suradnji*/
    @Override
    public void getCollaborationData(int collaborationId) {
        mApiCompanyCollaborationsInteractor.getNeededCollaboration(collaborationId);
    }

    /**Javi interactoru da da napravi push obavijest*/
    @Override
    public void pushNotification(int developerId) {
        mApiCompanyCollaborationsInteractor.pushNotificationDeveloper(developerId);
    }

    /**Javi viewu da su aplikanti stigli*/
    @Override
    public void onApplicantListCome(List<Applicant> applicants) {
        mCompanyNeededCollabView.onApplicantsArrived(applicants);
    }

    /**Javi viewu da je lista kompetencija stigla*/
    @Override
    public void onSkillListCome(List<String> skills) {
        mCompanyNeededCollabView.onSkillsListArrived(skills);
    }

    /**Javi interactoru da prihvati aplikanta */
    @Override
    public void applicantChosen(int collaborationId, int applicantId) {
        mApiCompanyCollaborationsInteractor.applicationAccepted(collaborationId, applicantId);
    }

    /**Javi viewu da su podatci u suradnji stigli*/
    @Override
    public void neededCollaborationDataArrived(NeededCollaborationData collaborationData) {
        mCompanyNeededCollabView.onCollaborationDataArrived(collaborationData);
    }

    /**Javi viewu da je apliciranje uspjesno */
    @Override
    public void onSuccessfulAssign() {
        mCompanyNeededCollabView.onSuccessfullAssign();
    }

    /**Javi viewu da je napravljena push obavijest*/
    @Override
    public void notificationPushedDeveloper() {
        mCompanyNeededCollabView.onPushSuccessful();
    }
}
