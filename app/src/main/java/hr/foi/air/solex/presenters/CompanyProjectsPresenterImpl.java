package hr.foi.air.solex.presenters;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProjectsView;

public class CompanyProjectsPresenterImpl implements CompanyProjectsPresenter, ProfileScreenProjectListListener {
    private CompanyProjectsView mCompanyProjectsView;
    private ProfileScreenProjectInteractor mProjectInteractor;

    public CompanyProjectsPresenterImpl(CompanyProjectsView CompanyProjectsView) {
        this.mCompanyProjectsView = CompanyProjectsView;
        this.mProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
    }

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mCompanyProjectsView.onDataArrived(projects);
    }

    @Override
    public void getProjects(int id) {
        mProjectInteractor.getAllProjectList(id);
    }
}
