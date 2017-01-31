package hr.foi.air.solex.models.favorites;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**POJO klasa favorita */
public class WSResponseFavourite {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("companyID")
    @Expose
    private int companyID;

    @SerializedName("developerID")
    @Expose
    int developerID;

    @SerializedName("status")
    @Expose
    String status;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
