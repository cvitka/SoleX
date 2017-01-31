package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.collaboration.NeededCollaboration;

public interface NewNeededCollaborationPresenter {
    /**Dodaj suradnju */
    void addNeededCollaboration(NeededCollaboration neededCollaboration);
    /**Dohvati kompetencije */
    void getAllSkills();
}
