package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectSearchResultView;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.models.searched_project.SearchedProjectAndroidInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractor;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectListListener;

public class ProjectSearchResultPresenterImpl implements ProjectSearchResultPresenter,SearchedProjectListListener {

    private ProjectSearchResultView mProjectSearchResultView;
    private SearchedProjectInteractor mPearchedProjectInteractor;

    public ProjectSearchResultPresenterImpl(ProjectSearchResultView projectSearchResultView) {
        mProjectSearchResultView = projectSearchResultView;
        //mPearchedProjectInteractor = new SearchedProjectAndroidInteractorImpl();
        mPearchedProjectInteractor = new SearchedProjectInteractorImpl();
        mPearchedProjectInteractor.setSearchedProjectListListener(this);
    }

    /**Javi viewu da su trazeni projekti stigli*/
    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        mProjectSearchResultView.onProjectListCome(projects);
    }

    /**Javi interactoru da dohvati trazene projekte */
    @Override
    public void getSearchedProjects(int percentage, List<String> skills) {
        mPearchedProjectInteractor.searchProjects(percentage,skills);
    }
}
