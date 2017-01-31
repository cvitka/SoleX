package hr.foi.air.solex.presenters.companies;

public interface CompanyProjectsPresenter {
    /**Dohvati projekte */
    void getProjects(int id);
    /**Dodaj u istaknute */
    void addToHighlights(int id);
    /**Ukloni istaknut */
    void removeHighlights(int id);
}
