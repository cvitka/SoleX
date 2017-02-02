package hr.foi.air.solex.models.modularity;

import java.util.List;

public interface SearchedProjectInteractor {
    /**Pretrazi projekte po posttku podudarnosti kompetencija */
    void searchProjects(List<String> skills);
    /**Javi da su stigli trazeni projekti  */
    void setSearchedProjectListListener(SearchedProjectListListener listListener);
}
