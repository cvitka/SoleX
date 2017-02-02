package hr.foi.air.solex.fragments;

import java.util.List;

import hr.foi.air.solex.models.modularity.SearchedProject;

public interface ProjectSearchDisplayView {
    void onProjectListCome(List<SearchedProject> projects);  /** stigli projekti */
}
