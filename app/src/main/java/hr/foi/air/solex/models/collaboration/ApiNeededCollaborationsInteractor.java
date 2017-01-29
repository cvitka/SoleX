package hr.foi.air.solex.models.collaboration;

import hr.foi.air.solex.presenters.companies.ApplicationAcceptedListener;

public interface ApiNeededCollaborationsInteractor {
    void getData(int id);
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);
    void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener);
    void setApplicationAcceptedListener(ApplicationAcceptedListener listener);
    void setDeveloperAppliesListener(DeveloperAppliesListener listener);
    void getNeededCollaboration(int collaborationId);
    void getNeededCollaboration(int collaborationId, int developerId);
    void applicationAccepted(int collaborationId, int developerId);
    void developerApplied(int collaborationId, int developerId);
    void developerRemovedApply(int collaborationId, int developerId);
    void pushNotification(int companyId);
    void setPushNotificationListener(PushNotificationListenerDeveloper listener);
    void pushNotificationDeveloper(int developerId);
    void setPushNotificationListenerCompany(PushNotificationListenerCompany listenerCompany);
}
