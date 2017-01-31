package hr.foi.air.solex.models.skills;

import hr.foi.air.solex.utils.UserType;

public interface SkillsInteractor {
    ////(int id, String name, String surname, String address, String email, String number, String years, String image);

    /**Dohvati listu kompetencija za odregenog korisnika */
    public void getSkillList(int id, UserType user);

    /**Dohvati sve kompetencije */
    public void getAllSkillList();

    /**Ukloni kompetenciju za odredenog korisnika */
    public void deleteSkill(int id, String skill, UserType user);

    /**Dodaj kompetenciju za odredenog korisnika */
    public void addSkill(int id, String skill, UserType user);

    /**Javi da je kompetencija dodana */
    public void setSkillListListener(SkillListListener listener);

    /**javi da je kompetencija uklonjena */
    public void setDeleteListener(SkillDeleteListener listener);

    /**Javi da je kompetencija azurirana */
    public void setUpdateListener(SkillUpdateListener listener);

    /**javi da su dosle sve kompetencije */
    public void setAllSkillListListener(AllSkillListListener listener);

    /**Dohvati sve kompetencije za odredenu suradnju */
    void getCollaborationSkillList(int collaborationId);
}
