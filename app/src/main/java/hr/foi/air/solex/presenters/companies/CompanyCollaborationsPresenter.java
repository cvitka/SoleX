package hr.foi.air.solex.presenters.companies;

public interface CompanyCollaborationsPresenter {
    /**Dohvati suradnje */
    void getCollaborations();
    /**Dodaj u favorite */
    void addToFavorites(int id);
    /**Azuriraj favorite */
    void updateFavorites(int id);
    /**Ocijeni */
    void rate(int rating, int user, int collaborationId);
}
