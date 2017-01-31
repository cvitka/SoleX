package hr.foi.air.solex.models.collaboration;


public interface DeveloperAppliesListener {
    /**  Javi da je developerovo apliciranje prihvaceno */
    void onApplySuccessfull();
    /**Javi da je developerovo apliciranje odbijeno */
    void onApplyRemoveSuccessfull();
}
