package com.example.webservice.models.skills;

public interface SkillUpdateListener {
    public void updateSuccessful();
    public void updateFailed(String message);
}
