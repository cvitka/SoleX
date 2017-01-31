package hr.foi.air.solex.presenters.common;


public interface CollaborationPresenter {

    /**Dohvati postojece suradnje */
    void getExistingCollaboration(int collaborationId);

    /**Dohvati kompetencije za suradnju */
    void getSkillsForCollaboration(int collaborationId);
}
