package hr.foi.air.solex.presenters;

import com.example.webservice.models.projects.ApiProject;
import com.example.webservice.models.projects.ProjectInteractor;
import com.example.webservice.models.projects.ProjectInteractorImpl;
import com.example.webservice.models.projects.ProjectListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProjectsView;

public class CompanyProjectsPresenterImpl implements CompanyProjectsPresenter, ProjectListListener {
    CompanyProjectsView mCompanyProjectsView;
    ProjectInteractor mProjectInteractor;

    public CompanyProjectsPresenterImpl(CompanyProjectsView CompanyProjectsView, ProjectInteractorImpl ProjectInteractorImpl) {
        this.mCompanyProjectsView = CompanyProjectsView;
        this.mProjectInteractor = ProjectInteractorImpl;
        mProjectInteractor.setListListener(this);
    }



    @Override
    public void getProjects() {
        mProjectInteractor.getProjectList();
    }

    @Override
    public void onProjectListCome(List<ApiProject> projects) {
        mCompanyProjectsView.onDataArrived(projects);
    }
}
