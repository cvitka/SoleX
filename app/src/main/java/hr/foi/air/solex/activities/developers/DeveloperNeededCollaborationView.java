package hr.foi.air.solex.activities.developers;


import java.util.List;

import hr.foi.air.solex.models.collaboration.NeededCollaborationData;

public interface DeveloperNeededCollaborationView {
    void onCollaborationDataArrived(NeededCollaborationData collaborationData);
    void onSkillsArrived(List<String> Skills);
    void onApplySuccessfull();
    void onRemoveApplySucessfull();
    void pushSucessful();
}
