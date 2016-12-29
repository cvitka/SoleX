package com.example.webservice.models.projects;

public interface SelectedProjectInteractor {
    void getSelectedProjectData(int id);
    void setScalarListener(SelectedProjectListener listener);

}
