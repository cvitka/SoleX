package hr.foi.air.solex.presenters.common;


import java.util.List;

import hr.foi.air.solex.activities.common.ProjectListingView;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;
import hr.foi.air.solex.utils.UserType;

public class ProjectListingPresenterImpl implements ProjectListingPresenter, ProfileScreenProjectListListener {
    private ProjectListingView mProjectsView;
    private ProfileScreenProjectInteractor mProjectInteractor;

    public ProjectListingPresenterImpl(ProjectListingView CompanyProjectsView) {
        this.mProjectsView = CompanyProjectsView;
        this.mProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
    }

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mProjectsView.onProjectsArrived(projects);
    }

    @Override
    public void getProjects(int id, UserType userType) {
        mProjectInteractor.getAllProjectList(id, userType);
    }
}
