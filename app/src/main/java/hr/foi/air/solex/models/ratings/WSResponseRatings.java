package hr.foi.air.solex.models.ratings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**POJO klasa ocjena */
public class WSResponseRatings {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

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
}
