package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectsSearchFeelingLuckyView;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.models.searched_project.SearchedProjectAndroidInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractor;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectListListener;

public class ProjectSearchFeelingLuckyPresenterImpl implements ProjectSearchFeelingLuckyPresenter,SearchedProjectListListener {
    ProjectsSearchFeelingLuckyView mProjectsSearchFeelingLuckyView;
    SearchedProjectInteractor mSearchedProjectInteractor;

    public ProjectSearchFeelingLuckyPresenterImpl(ProjectsSearchFeelingLuckyView projectsSearchFeelingLuckyView) {
        mProjectsSearchFeelingLuckyView = projectsSearchFeelingLuckyView;

        //mSearchedProjectInteractor = new SearchedProjectInteractorImpl();
        mSearchedProjectInteractor = new SearchedProjectAndroidInteractorImpl();

        mSearchedProjectInteractor.setSearchedProjectListListener(this);

    }

    /**Javi interactoru da dohvati projekte*/
    @Override
    public void getSearchedProjects(List<String> skills) {
        mSearchedProjectInteractor.luckySearchProjects(skills);
    }

    /**Javi viewu da su projekti stigli*/
    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        mProjectsSearchFeelingLuckyView.onProjectListCome(projects);
    }
}
