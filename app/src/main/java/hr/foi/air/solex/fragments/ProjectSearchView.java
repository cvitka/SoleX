package hr.foi.air.solex.fragments;

import java.util.List;

import hr.foi.air.solex.models.searched_project.SearchedProject;

public interface ProjectSearchView {
    void allSkillsListArrived(List<String> list);
    void developerSkillsListArrived(List<String> list);
}
