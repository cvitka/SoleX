package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.R;
import hr.foi.air.solex.presenters.common.LoginPresenter;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.login_registration.LoginInteractor;
import hr.foi.air.solex.models.login_registration.LoginInteractorImpl;
import hr.foi.air.solex.models.login_registration.LoginResponseListener;
import hr.foi.air.solex.models.login_registration.User;

import hr.foi.air.solex.activities.common.LoginView;

/**
 * Created by Asus on 6.12.2016..
 */

public class LoginPresenterImpl implements LoginPresenter, LoginResponseListener {
    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void tryLogin(String username, String password) {
        mLoginInteractor.checkLogin(username, password);
    }

    //prebacio logiku kreiranja singletona u presenter (model bi trebao samo dobavljati podatke)
    @Override
    public void onLoginSuccessful(int id, String email, String type) {
        User.getInstance().setId(id);
        User.getInstance().setEmail(email);
        if (type.equals(UserType.COMPANY.stringVal())) {
            User.getInstance().setUserType(UserType.COMPANY);
            mLoginView.onCompanyLoginSuccess();
            //Company.getInstance().setId(response.body().getId());

        } else if (type.equals(UserType.DEVELOPER.stringVal())) {
            User.getInstance().setUserType(UserType.DEVELOPER);
            mLoginView.onDeveloperLoginSuccess();
           // Developer.getInstance().setId(response.body().getId());
        }
    }

    @Override
    public void onServerConnectionFailed() {
        mLoginView.onContactServerFailed();
    }

    @Override
    public void onLoginFailed(String message) {
        mLoginView.onLoginFailed();
    }
}
