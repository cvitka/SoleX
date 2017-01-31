package hr.foi.air.solex.presenters.developers;


public interface DeveloperCollaborationsPresenter {
    /**Dohvati suradnje */
    void getCollaborations(int developerId);
    /**Ocijeni */
    void rate(int rating, int user, int collaborationId);
}
