package hr.foi.air.solex.presenters.developers;


import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationView;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractor;
import hr.foi.air.solex.models.collaboration.ApiNeededCollaborationsInteractorImpl;
import hr.foi.air.solex.models.collaboration.DeveloperAppliesListener;
import hr.foi.air.solex.models.collaboration.NeededCollaborationData;
import hr.foi.air.solex.models.collaboration.NeededCollaborationDataScalarListener;
import hr.foi.air.solex.models.collaboration.PushNotificationListenerDeveloper;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

public class DeveloperNeededCollaborationPresenterImpl implements DeveloperNeededCollaborationPresenter, SkillListListener, NeededCollaborationDataScalarListener, DeveloperAppliesListener,PushNotificationListenerDeveloper {
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
        mApiCompanyCollaborationsInteractor.setPushNotificationListener(this);
    }

    /**Javi interactoru da dohvati kompetenricje za suradnju */
    @Override
    public void getCollaborationSkills(int collaborationId) {
        mSkillsInteractor.getCollaborationSkillList(collaborationId);
    }

    /**Javi interactoru da dohvati podatke o suradnji*/
    @Override
    public void getCollaborationData(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.getNeededCollaboration(collaborationId, developerId);
    }

    /**Javi viewu da je stigla lista kompetencija za suradnju*/
    @Override
    public void onSkillListCome(List<String> skills) {
        mDeveloperNeededCollaborationView.onSkillsArrived(skills);
    }

    /**Javi viewu da su podatci o suradnji stigli*/
    @Override
    public void neededCollaborationDataArrived(NeededCollaborationData collaborationData) {
        mDeveloperNeededCollaborationView.onCollaborationDataArrived(collaborationData);
    }

    /**Javi viewu da paliciranje prihvaceno */
    @Override
    public void onApplySuccessfull() {
        mDeveloperNeededCollaborationView.onApplySuccessfull();
    }

    /**Javi viewu da je apliciranje odbijeno*/
    @Override
    public void onApplyRemoveSuccessfull() {
        mDeveloperNeededCollaborationView.onRemoveApplySucessfull();
    }

    /**Javi interactoru da prihvati apliciranje*/
    @Override
    public void apply(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.developerApplied(collaborationId, developerId);
    }

    /**Javi interactoru da odbije apliciranje*/
    @Override
    public void removeApply(int collaborationId, int developerId) {
        mApiCompanyCollaborationsInteractor.developerRemovedApply(collaborationId, developerId);
    }

    /**Javi interactoru da napravi push obavijest*/
    @Override
    public void pushNotif(int companyId) {
        mApiCompanyCollaborationsInteractor.pushNotification(companyId);
    }

    /**Javi viewu da je push obavijest uspjesna*/
    @Override
    public void notificationPushedCompany() {
        mDeveloperNeededCollaborationView.pushSucessful();
    }
}
