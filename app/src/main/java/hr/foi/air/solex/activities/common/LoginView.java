package hr.foi.air.solex.activities.common;


public interface LoginView {
    public void onDeveloperLoginSuccess();
    public void onCompanyLoginSuccess();
    public void onLoginFailed();
    public void onContactServerFailed();
}
