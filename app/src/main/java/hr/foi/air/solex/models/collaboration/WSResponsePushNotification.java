package hr.foi.air.solex.models.collaboration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResponsePushNotification {
    @SerializedName("multicast_id")
    @Expose
    private String multicast_id;

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("failure")
    @Expose
    private String failure;

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }
}
