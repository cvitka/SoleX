package hr.foi.air.solex.presenters.companies;

public interface CompanyCollaborationsPresenter {
    void getCollaborations();
    void addToFavorites(int id);
    void updateFavorites(int id);
    void rate(int rating, int user, int collaborationId);
}
