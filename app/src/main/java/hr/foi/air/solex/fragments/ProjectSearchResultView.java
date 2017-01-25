package hr.foi.air.solex.fragments;

import java.util.List;

import hr.foi.air.solex.models.searched_project.SearchedProject;

public interface ProjectSearchResultView {
    void onProjectListCome(List<SearchedProject> projects);
}
