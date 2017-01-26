package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectSearchResultView;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractor;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectListListener;

public class ProjectSearchResultPresenterImpl implements ProjectSearchResultPresenter,SearchedProjectListListener {

    ProjectSearchResultView mProjectSearchResultView;
    SearchedProjectInteractor mPearchedProjectInteractor;

    public ProjectSearchResultPresenterImpl(ProjectSearchResultView projectSearchResultView) {
        mProjectSearchResultView = projectSearchResultView;
        mPearchedProjectInteractor = new SearchedProjectInteractorImpl();
        mPearchedProjectInteractor.setSearchedProjectListListener(this);
    }

    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        mProjectSearchResultView.onProjectListCome(projects);
    }

    @Override
    public void getSearchedProjects(int percentage, List<String> skills) {
        mPearchedProjectInteractor.searchProjects(percentage,skills);
    }
}
