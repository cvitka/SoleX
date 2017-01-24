package hr.foi.air.solex.models.skills;

public interface SkillUpdateListener {
    public void updateSuccessful();
    public void updateFailed(String message);
}
