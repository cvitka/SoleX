package hr.foi.air.solex.models.skills;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**POJO klasa ws odgovora */
public class WSResponseSkill {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("strucnost")
    @Expose
    private String skill;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

}
