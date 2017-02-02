package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectSearchDisplayView;
import hr.foi.air.solex.models.modularity.SearchedProject;
import hr.foi.air.solex.models.modularity.SearchedProjectInteractor;
import hr.foi.air.solex.models.modularity.SearchedProjectListListener;

public class ProjectSearchDisplayPresenterImpl implements ProjectSearchDisplayPresenter,SearchedProjectListListener {
    ProjectSearchDisplayView mProjectsSearchFeelingLuckyView;
    SearchedProjectInteractor mSearchedProjectInteractor;

    public ProjectSearchDisplayPresenterImpl(ProjectSearchDisplayView projectsSearchFeelingLuckyView) {
        mProjectsSearchFeelingLuckyView = projectsSearchFeelingLuckyView;



    }

    @Override
    public void setInteractor(SearchedProjectInteractor interactor) {
        mSearchedProjectInteractor = interactor;
        mSearchedProjectInteractor.setSearchedProjectListListener(this);
    }

    /**Javi interactoru da dohvati projekte*/
    @Override
    public void getSearchedProjects(List<String> skills) {
        mSearchedProjectInteractor.searchProjects(skills);
    }

    /**Javi viewu da su projekti stigli*/
    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        mProjectsSearchFeelingLuckyView.onProjectListCome(projects);
    }
}
