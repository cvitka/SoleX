package hr.foi.air.solex.models.collab_applicat;


import java.util.List;
/** Listener za listu podataka sa web servisa */
public interface ApplicatListListener {
    public void applicatListArrived(List<CollabApplicat> list);
}
