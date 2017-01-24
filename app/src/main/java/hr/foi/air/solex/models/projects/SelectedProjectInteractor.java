package hr.foi.air.solex.models.projects;

public interface SelectedProjectInteractor {
    void getSelectedProjectData(int id);
    void setScalarListener(SelectedProjectListener listener);

}
