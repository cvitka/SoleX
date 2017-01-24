package hr.foi.air.solex.presenters;

import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.signup.CompanySignupInteractor;
import hr.foi.air.solex.models.signup.CompanySignupInteractorImpl;
import hr.foi.air.solex.models.signup.SignUpResponseListener;

import hr.foi.air.solex.fragments.SignupView;

/**
 * Created by Asus on 7.12.2016..
 */

public class CompanySignupPresenterImpl implements CompanySignupPresenter, SignUpResponseListener {

    private SignupView mCompanySignupView;
    private CompanySignupInteractor mCompanySignupInteractor;

    public CompanySignupPresenterImpl(SignupView companySignupView) {
        this.mCompanySignupView = companySignupView;
        this.mCompanySignupInteractor = new CompanySignupInteractorImpl(this);
    }

    @Override
    public void tryRegister(Company company, String password) {
        mCompanySignupInteractor.registerCompany(company, password);
    }

    @Override
    public void onRegistrationSuccessful() {
        mCompanySignupView.signupSuccessful();
    }

    @Override
    public void onServerConnectionFailed() {
        mCompanySignupView.signupFailed("Cannot connect to server");
    }

    @Override
    public void onRegistrationFailed(String message) {
        mCompanySignupView.signupFailed(message);
    }
}
