package hr.foi.air.solex.models.collaboration;

import hr.foi.air.solex.presenters.companies.ApplicationAcceptedListener;

public interface ApiNeededCollaborationsInteractor {
    /**Dohvacanje potrebnih suradnji za poduzece. Id poduzeca */
    void getData(int id);

    /** Javi da je lista svih potrebih suradnji stigla */
    void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener);

    /** Javi da su stigli podatci za potrebu suradnju */
    void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener);

    /** Javi da je apliciranje uspjesno obavljeno*/
    void setApplicationAcceptedListener(ApplicationAcceptedListener listener);

    /** Javi da se developer prijavio za mjesto */
    void setDeveloperAppliesListener(DeveloperAppliesListener listener);

    /** Dohvacanje suradnji za poduzece*/
    void getNeededCollaboration(int collaborationId);

    /**Dohvati suradnju za odredenog developera i odredeno poduzece */
    void getNeededCollaboration(int collaborationId, int developerId);

    /** Dodijeli posao */
    void applicationAccepted(int collaborationId, int developerId);

    /** Apliciraj developera za odredenu suradnju*/
    void developerApplied(int collaborationId, int developerId);

    /** Odbij develeopera za odredeno apliciranje */
    void developerRemovedApply(int collaborationId, int developerId);

    /** Push obavijest poduzecu za novo apliciranje*/
    void pushNotification(int companyId);

    /** Javi da je pushana obavijest developeru */
    void setPushNotificationListener(PushNotificationListenerDeveloper listener);

    /**Push obavijest develoepru */
    void pushNotificationDeveloper(int developerId);

    /**javi da je pushana obavijest developeru */
    void setPushNotificationListenerCompany(PushNotificationListenerCompany listenerCompany);
}
