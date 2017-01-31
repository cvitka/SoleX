package hr.foi.air.solex.fragments;

import java.util.List;

import hr.foi.air.solex.models.searched_project.SearchedProject;

public interface ProjectSearchView {
    void allSkillsListArrived(List<String> list);  /** stigli sve vjestine u obliku liste*/
    void developerSkillsListArrived(List<String> list);  /** stigli sve vjestine developera u obliku liste */
}
