package hr.foi.air.solex.presenters;

import com.example.webservice.models.projects.Project;
import com.example.webservice.models.projects.SelectedProjectInteractor;
import com.example.webservice.models.projects.SelectedProjectListener;

import hr.foi.air.solex.activities.companies.ProjectManagementView;

public class ProjectManagementPresenterImpl implements ProjectManagementPresenter, SelectedProjectListener {

    ProjectManagementView mProjectManagementView;
    SelectedProjectInteractor mInteractor;

    public ProjectManagementPresenterImpl(ProjectManagementView mProjectManagementView, SelectedProjectInteractor mInteractor) {
        this.mProjectManagementView = mProjectManagementView;
        this.mInteractor = mInteractor;
        mInteractor.setScalarListener(this);
    }

    @Override
    public void getProject(int id) {
        mInteractor.getSelectedProjectData(id);
    }

    @Override
    public void onDataCome(Project project) {
        mProjectManagementView.DataArrived(project);
    }
}
