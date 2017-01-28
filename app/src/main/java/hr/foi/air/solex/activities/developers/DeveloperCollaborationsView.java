package hr.foi.air.solex.activities.developers;


import java.util.List;

import hr.foi.air.solex.models.collab_applicat.CollabApplicat;

public interface DeveloperCollaborationsView {
    void onCollaborationsArrived(List<CollabApplicat> list);
    void onRateSucceeded();
}
