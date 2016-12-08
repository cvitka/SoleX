package hr.foi.air.solex.presenters;

import com.example.webservice.models.Companies.Company;
import com.example.webservice.models.signup.CompanySignupModel;
import com.example.webservice.models.signup.CompanySignupModelImpl;
import com.example.webservice.models.signup.SignUpResponseListener;

import hr.foi.air.solex.fragments.SignupView;

/**
 * Created by Asus on 7.12.2016..
 */

public class CompanySignupPresenterImpl implements CompanySignupPresenter, SignUpResponseListener {

    private SignupView mCompanySignupView;
    private CompanySignupModel mCompanySignupModel;

    public CompanySignupPresenterImpl(SignupView companySignupView) {
        this.mCompanySignupView = companySignupView;
        this.mCompanySignupModel = new CompanySignupModelImpl(this);
    }

    @Override
    public void tryRegister(Company company, String password) {
        mCompanySignupModel.registerCompany(company, password);
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
