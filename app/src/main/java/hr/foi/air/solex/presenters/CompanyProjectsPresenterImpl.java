package hr.foi.air.solex.presenters;

import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractor;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectListListener;
import com.example.webservice.models.projects.ApiProject;
import com.example.webservice.models.projects.ProjectInteractor;
import com.example.webservice.models.projects.ProjectInteractorImpl;
import com.example.webservice.models.projects.ProjectListListener;

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
