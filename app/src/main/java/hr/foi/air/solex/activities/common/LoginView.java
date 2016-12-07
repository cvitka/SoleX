package hr.foi.air.solex.activities.common;

/**
 * Created by Asus on 6.12.2016..
 */

public interface LoginView {
    public void onDeveloperLoginSuccess();
    public void onCompanyLoginSuccess();
    public void onLoginFailed(String message);
}
