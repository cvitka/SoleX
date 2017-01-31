package hr.foi.air.solex.models.login_registration;

/**Listener za prijavljivanje. Javi uspjeh ili gresku  */
public interface LoginResponseListener {
    public void onLoginSuccessful(int id, String email, String type);
    public void onServerConnectionFailed();
    public void onLoginFailed(String message);
}
