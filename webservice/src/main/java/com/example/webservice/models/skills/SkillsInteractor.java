package com.example.webservice.models.skills;

import com.example.core.utils.UserType;

public interface SkillsInteractor {
    public void getSkillList(int id, UserType user);//(int id, String name, String surname, String address, String email, String number, String years, String image);
    public void getAllSkillList();
    public void deleteSkill(int id, String skill, UserType user);
    public void addSkill(int id, String skill, UserType user);
    public void setSkillListListener(SkillListListener listener);
    public void setDeleteListener(SkillDeleteListener listener);
    public void setUpdateListener(SkillUpdateListener listener);
    public void setAllSkillListListener(AllSkillListListener listener);
}