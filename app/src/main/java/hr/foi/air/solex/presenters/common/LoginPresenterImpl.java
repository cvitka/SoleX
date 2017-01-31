package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.R;
import hr.foi.air.solex.presenters.common.LoginPresenter;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.login_registration.LoginInteractor;
import hr.foi.air.solex.models.login_registration.LoginInteractorImpl;
import hr.foi.air.solex.models.login_registration.LoginResponseListener;
import hr.foi.air.solex.models.login_registration.User;

import hr.foi.air.solex.activities.common.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginResponseListener {

    private LoginView mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.mLoginView = loginView;
        this.mLoginInteractor = new LoginInteractorImpl(this);
    }

    /**Javi interactoru da pokusa izvrsiti prijavu */
    @Override
    public void tryLogin(String username, String password) {
        mLoginInteractor.checkLogin(username, password);
    }

    //prebacio logiku kreiranja singletona u presenter (model bi trebao samo dobavljati podatke)
    /**Javi viewu da je prijava uspjela */
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

    /**javi viewu da je greska prilikom povezivanja na server */
    @Override
    public void onServerConnectionFailed() {
        mLoginView.onContactServerFailed();
    }

    /**javi viewu da prijava nije uspjela */
    @Override
    public void onLoginFailed(String message) {
        mLoginView.onLoginFailed();
    }
}
