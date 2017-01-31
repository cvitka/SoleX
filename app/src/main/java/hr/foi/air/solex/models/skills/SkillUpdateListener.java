package hr.foi.air.solex.models.skills;


/**Listener za azuriranje kompetencija */
public interface SkillUpdateListener {
    public void updateSuccessful();
    public void updateFailed(String message);
}
