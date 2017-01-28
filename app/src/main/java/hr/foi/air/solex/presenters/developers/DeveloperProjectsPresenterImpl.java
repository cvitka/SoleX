package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperProjectsView;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.AddHighlightListener;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;
import hr.foi.air.solex.models.profile_screen_project.RemoveHighlightListener;
import hr.foi.air.solex.utils.UserType;

public class DeveloperProjectsPresenterImpl implements DeveloperProjectsPresenter, ProfileScreenProjectListListener, AddHighlightListener, RemoveHighlightListener {
    private DeveloperProjectsView developerProjectsView;
    private ProfileScreenProjectInteractor mProjectInteractor;

    public DeveloperProjectsPresenterImpl(DeveloperProjectsView developerProjectsView) {
        this.developerProjectsView = developerProjectsView;
        this.mProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
        mProjectInteractor.setAddHighlightListener(this);
        mProjectInteractor.setRemoveHighlightListener(this);
    }

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        developerProjectsView.onDataArrived(projects);
    }

    @Override
    public void getProjects(int id) {
        mProjectInteractor.getAllProjectList(id, UserType.DEVELOPER);
    }

    @Override
    public void addToHighlights(int id) {
        mProjectInteractor.addToHighlighted(id, User.getInstance().getId(), User.getInstance().getUserType());
    }

    @Override
    public void removeHighlights(int id) {
        mProjectInteractor.removeHighlighted(id, User.getInstance().getId(), User.getInstance().getUserType());
    }

    @Override
    public void onRemove() {
        developerProjectsView.onHighlightRemove();
    }

    @Override
    public void onRemoveFailure(String message) {
        developerProjectsView.onHighlightRemoveFailure(message);
    }

    @Override
    public void onHighlightsAdd() {
        developerProjectsView.onHighlightAddition();
    }

    @Override
    public void onHighlightsAddFailure(String message) {
        developerProjectsView.onHighlightFailure(message);
    }
}
