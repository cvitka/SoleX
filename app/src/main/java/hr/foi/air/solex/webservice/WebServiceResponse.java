package hr.foi.air.solex.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hr.foi.air.solex.models.Developers;

/**
 * Created by cvitka on 10.11.16..
 */

public class WebServiceResponse {


    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*   @SerializedName("developer")
    @Expose
    private Developers developer;*/


    public WebServiceResponse() {
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

   /* public Developers getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developers developer) {
        this.developer = developer;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
