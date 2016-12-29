package hr.foi.air.solex.presenters;

import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.signup.DeveloperSignupModel;
import com.example.webservice.models.signup.DeveloperSignupModelImpl;
import com.example.webservice.models.signup.SignUpResponseListener;

import hr.foi.air.solex.fragments.SignupView;

/**
 * Created by Asus on 7.12.2016..
 */

public class DeveloperSignupPresenterImpl implements DeveloperSignupPresenter, SignUpResponseListener {

    private SignupView mDeveloperSignupView;
    private DeveloperSignupModel mDeveloperSignupModel;

    public DeveloperSignupPresenterImpl(SignupView companySignupView) {
        this.mDeveloperSignupView = companySignupView;
        this.mDeveloperSignupModel = new DeveloperSignupModelImpl(this);
    }

    @Override
    public void tryRegister(Developer developer, String password) {
        mDeveloperSignupModel.registerDeveloper(developer, password);
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
