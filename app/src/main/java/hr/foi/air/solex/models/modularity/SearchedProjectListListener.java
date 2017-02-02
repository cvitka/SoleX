package hr.foi.air.solex.models.modularity;


import java.util.List;

/**Listener za listu projekata */
public interface SearchedProjectListListener {
    void onProjectListCome(List<SearchedProject> projects);
}
