package hr.foi.air.solex.models.searched_project;

import java.util.List;

import hr.foi.air.solex.utils.UserType;

public interface SearchedProjectInteractor {
    void searchProjects(int percentage, List<String>skills);
    void luckySearchProjects(List<String> skills);
    void setSearchedProjectListListener(SearchedProjectListListener listListener);
}
