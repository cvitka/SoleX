package hr.foi.air.solex.models.searched_project;

import java.util.List;

import hr.foi.air.solex.utils.UserType;

public interface SearchedProjectInteractor {
    /**Pretrazi projekte po posttku podudarnosti kompetencija */
    void searchProjects(int percentage, List<String>skills);
    /**Lucky search */
    void luckySearchProjects(List<String> skills);
    /**Javi da su stigli trazeni projekti  */
    void setSearchedProjectListListener(SearchedProjectListListener listListener);
}
