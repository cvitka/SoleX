package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.models.modularity.SearchedProjectInteractor;

public interface ProjectSearchDisplayPresenter {
    /**Dohvati projekte */
    public void getSearchedProjects(List<String> skills);
    public void setInteractor(SearchedProjectInteractor interactor);
}
