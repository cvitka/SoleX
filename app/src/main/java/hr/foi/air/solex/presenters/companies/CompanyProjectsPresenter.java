package hr.foi.air.solex.presenters.companies;

public interface CompanyProjectsPresenter {
    void getProjects(int id);
    void addToHighlights(int id);
    void updateHighlights(int id);
}
