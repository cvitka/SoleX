package hr.foi.air.solex.models.signup;

/**
 * Created by Asus on 6.12.2016..
 */

public interface SignUpResponseListener {
    public void onRegistrationSuccessful();
    public void onServerConnectionFailed();
    public void onRegistrationFailed(String message);
}
