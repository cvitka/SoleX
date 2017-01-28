package hr.foi.air.solex.presenters.developers;

public interface DeveloperProjectsPresenter {
    void getProjects(int id);
    void addToHighlights(int id);
    void removeHighlights(int id);
}
