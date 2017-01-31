package hr.foi.air.solex.models.signup;

/**Listener za registraciju */
public interface SignUpResponseListener {
    public void onRegistrationSuccessful();
    public void onServerConnectionFailed();
    public void onRegistrationFailed(String message);
}
