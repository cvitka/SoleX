package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProjectsView;
import hr.foi.air.solex.models.profile_screen_project.AddHighlightListener;
import hr.foi.air.solex.models.profile_screen_project.RemoveHighlightListener;

public class CompanyProjectsPresenterImpl implements CompanyProjectsPresenter, ProfileScreenProjectListListener, AddHighlightListener, RemoveHighlightListener {

    private CompanyProjectsView mCompanyProjectsView;
    private ProfileScreenProjectInteractor mProjectInteractor;

    public CompanyProjectsPresenterImpl(CompanyProjectsView CompanyProjectsView) {
        this.mCompanyProjectsView = CompanyProjectsView;
        this.mProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
        mProjectInteractor.setAddHighlightListener(this);
        mProjectInteractor.setRemoveHighlightListener(this);
    }

    /**Javi viewu da da su projekti stigli*/
    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mCompanyProjectsView.onDataArrived(projects);
    }

    /**Javi interactoru da dohvati sve projekte za poduzece*/
    @Override
    public void getProjects(int id) {
        mProjectInteractor.getAllProjectList(id);
    }

    /**Javi interactoru da doda projekt i itaknute*/
    @Override
    public void addToHighlights(int id) {
        mProjectInteractor.addToHighlighted(id, User.getInstance().getId(), User.getInstance().getUserType());
    }

    /**Javi interactoru da makne projekt iz istaknutih*/
    @Override
    public void removeHighlights(int id) {
        mProjectInteractor.removeHighlighted(id, User.getInstance().getId(), User.getInstance().getUserType());
    }

    /**Javi viewu da je projekt maknut iz istaknutih*/
    @Override
    public void onRemove() {
        mCompanyProjectsView.onHighlightRemove();
    }

    /**Javi viewu da da uklanjanje projekta iz istaknutih nije uspjelo*/
    @Override
    public void onRemoveFailure(String message) {
        mCompanyProjectsView.onHighlightRemoveFailure(message);
    }

    /**Javi viewu da je dodan projekt u istaknute*/
    @Override
    public void onHighlightsAdd() {
        mCompanyProjectsView.onHighlightAddition();
    }

    /**Javi viewu da projekt nije dodan u istaknute*/
    @Override
    public void onHighlightsAddFailure(String message) {
        mCompanyProjectsView.onHighlightFailure(message);
    }
}
