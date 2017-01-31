package hr.foi.air.solex.presenters.developers;

public interface DeveloperProjectsPresenter {
    /**Dohvati projekte */
    void getProjects(int id);
    /**Dodaj u istaknute */
    void addToHighlights(int id);
    /** Ukloni istaknuti*/
    void removeHighlights(int id);
}
