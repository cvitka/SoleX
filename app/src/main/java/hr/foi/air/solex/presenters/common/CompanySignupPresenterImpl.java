package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.signup.CompanySignupInteractor;
import hr.foi.air.solex.models.signup.CompanySignupInteractorImpl;
import hr.foi.air.solex.models.signup.SignUpResponseListener;

import hr.foi.air.solex.fragments.SignupView;
import hr.foi.air.solex.presenters.common.CompanySignupPresenter;


public class CompanySignupPresenterImpl implements CompanySignupPresenter, SignUpResponseListener {

    private SignupView mCompanySignupView;
    private CompanySignupInteractor mCompanySignupInteractor;

    public CompanySignupPresenterImpl(SignupView companySignupView) {
        /**Postavljanje viewa i interactora */
        this.mCompanySignupView = companySignupView;
        this.mCompanySignupInteractor = new CompanySignupInteractorImpl(this);
    }

    /**Javi interactoru da izvrsi registraciju */
    @Override
    public void tryRegister(Company company, String password) {
        mCompanySignupInteractor.registerCompany(company, password);
    }

    /**Javi viewu da je registracija uspjesno prosla */
    @Override
    public void onRegistrationSuccessful() {
        mCompanySignupView.signupSuccessful();
    }

    /**Javi viewu da registracija nije uspjesno prosla */
    @Override
    public void onServerConnectionFailed() {
        mCompanySignupView.signupFailed("Cannot connect to server");
    }

    @Override
    public void onRegistrationFailed(String message) {
        mCompanySignupView.signupFailed(message);
    }
}
