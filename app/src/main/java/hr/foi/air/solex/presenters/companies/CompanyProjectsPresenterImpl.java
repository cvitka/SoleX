package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProjectsView;
import hr.foi.air.solex.models.profile_screen_project.AddHighlightListener;
import hr.foi.air.solex.models.profile_screen_project.UpdateHighlightListener;

public class CompanyProjectsPresenterImpl implements CompanyProjectsPresenter, ProfileScreenProjectListListener, AddHighlightListener, UpdateHighlightListener {
    private CompanyProjectsView mCompanyProjectsView;
    private ProfileScreenProjectInteractor mProjectInteractor;

    public CompanyProjectsPresenterImpl(CompanyProjectsView CompanyProjectsView) {
        this.mCompanyProjectsView = CompanyProjectsView;
        this.mProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
        mProjectInteractor.setAddHighlightListener(this);
        mProjectInteractor.setUpdateHighlightListener(this);
    }

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mCompanyProjectsView.onDataArrived(projects);
    }

    @Override
    public void getProjects(int id) {
        mProjectInteractor.getAllProjectList(id);
    }

    @Override
    public void addToHighlights(int id) {
        mProjectInteractor.addToHighlighted(id);
    }

    @Override
    public void updateHighlights(int id) {
        mProjectInteractor.updateToHighlighted(id);
    }

    @Override
    public void onUpdate() {
        mCompanyProjectsView.onHighlightUpdate();
    }

    @Override
    public void onUpdateFailure(String message) {
        mCompanyProjectsView.onHighlightUpdateFailure(message);
    }

    @Override
    public void onHighlightsAdd() {
        mCompanyProjectsView.onHighlightAddition();
    }

    @Override
    public void onHighlightsAddFailure(String message) {
        mCompanyProjectsView.onHighlightFailure(message);
    }
}
