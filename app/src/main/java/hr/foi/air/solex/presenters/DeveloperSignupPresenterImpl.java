package hr.foi.air.solex.presenters;

import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.signup.DeveloperSignupInteractor;
import com.example.webservice.models.signup.DeveloperSignupInteractorImpl;
import com.example.webservice.models.signup.SignUpResponseListener;

import hr.foi.air.solex.fragments.SignupView;

/**
 * Created by Asus on 7.12.2016..
 */

public class DeveloperSignupPresenterImpl implements DeveloperSignupPresenter, SignUpResponseListener {

    private SignupView mDeveloperSignupView;
    private DeveloperSignupInteractor mDeveloperSignupInteractor;

    public DeveloperSignupPresenterImpl(SignupView companySignupView) {
        this.mDeveloperSignupView = companySignupView;
        this.mDeveloperSignupInteractor = new DeveloperSignupInteractorImpl(this);
    }

    @Override
    public void tryRegister(Developer developer, String password) {
        mDeveloperSignupInteractor.registerDeveloper(developer, password);
    }

    @Override
    public void onRegistrationSuccessful() {
        mDeveloperSignupView.signupSuccessful();
    }

    @Override
    public void onServerConnectionFailed() {
        mDeveloperSignupView.signupFailed("Cannot connect to server");
    }

    @Override
    public void onRegistrationFailed(String message) {
        mDeveloperSignupView.signupFailed(message);
    }
}
