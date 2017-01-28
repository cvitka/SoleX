package hr.foi.air.solex.presenters.developers;


public interface DeveloperCollaborationsPresenter {
    void getCollaborations(int developerId);
    void rate(int rating, int user, int collaborationId);
}
