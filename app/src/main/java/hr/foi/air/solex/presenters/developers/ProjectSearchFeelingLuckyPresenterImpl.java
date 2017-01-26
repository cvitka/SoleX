package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectsSearchFeelingLuckyView;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractor;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectListListener;

public class ProjectSearchFeelingLuckyPresenterImpl implements ProjectSearchFeelingLuckyPresenter,SearchedProjectListListener {
    ProjectsSearchFeelingLuckyView mProjectsSearchFeelingLuckyView;
    SearchedProjectInteractor mSearchedProjectInteractor;

    public ProjectSearchFeelingLuckyPresenterImpl(ProjectsSearchFeelingLuckyView projectsSearchFeelingLuckyView) {
        mProjectsSearchFeelingLuckyView = projectsSearchFeelingLuckyView;
        mSearchedProjectInteractor = new SearchedProjectInteractorImpl();
        mSearchedProjectInteractor.setSearchedProjectListListener(this);

    }

    @Override
    public void getSearchedProjects(List<String> skills) {
        mSearchedProjectInteractor.luckySearchProjects(skills);
    }

    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        mProjectsSearchFeelingLuckyView.onProjectListCome(projects);
    }
}
