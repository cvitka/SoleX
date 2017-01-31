package hr.foi.air.solex.models.ratings;

import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillDeleteListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillUpdateListener;
import hr.foi.air.solex.utils.UserType;

/**Ocijeni poduzece/developera */
public interface RatingsInteractor {
    void rate(int rating, int user, int collaborationId);
}
