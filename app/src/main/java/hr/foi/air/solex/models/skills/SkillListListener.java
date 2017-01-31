package hr.foi.air.solex.models.skills;

import java.util.List;

/**Listener za dolazak liste kompetencija */
public interface SkillListListener {
    void onSkillListCome(List<String> skills);
}
