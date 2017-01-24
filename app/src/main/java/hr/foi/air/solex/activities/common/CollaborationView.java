package hr.foi.air.solex.activities.common;


import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;

import java.util.List;

public interface CollaborationView {
    void onCollaborationArrived(ApiCompanyCollaborations collaboration);
    void onSkillsArrived(List<String> skills);
}
