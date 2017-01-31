package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.models.searched_project.SearchedProject;

public interface ProjectSearchResultPresenter {
    /**Dohvati trazene projekte */
    public void getSearchedProjects(int percentage, List<String> skills);
}
