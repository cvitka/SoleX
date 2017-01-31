package hr.foi.air.solex.models.projects;

public interface SelectedProjectInteractor {
    /**Dohvati podatke za odabrani projekt */
    void getSelectedProjectData(int id);
    /**javi da su podatci stigli */
    void setScalarListener(SelectedProjectListener listener);

}
