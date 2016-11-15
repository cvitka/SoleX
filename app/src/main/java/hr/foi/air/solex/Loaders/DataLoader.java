package hr.foi.air.solex.Loaders;

import android.app.Activity;
import android.content.Intent;

import com.example.core.utils.LoginStatus;
import com.example.core.utils.UserType;
import com.example.webservice.WebServiceHandler;
import com.example.webservice.models.User;

import hr.foi.air.solex.CompanyProfileActivity;
import hr.foi.air.solex.DeveloperProfileActivity;
import hr.foi.air.solex.LoginActivity;
import hr.foi.air.solex.SignupCompanyFragment;
import hr.foi.air.solex.SignupDeveloperFragment;

/**
 * Created by cvitka on 11.11.16..
 */

public class DataLoader implements WebServiceHandler{

    Activity mActivity;
    LoginActivity mLoginActivity;

    SignupDeveloperFragment mFragment;

    SignupCompanyFragment cFragment;
    
    public DataLoader(LoginActivity loginActivity) {
        this.mLoginActivity = loginActivity;
    }


    public DataLoader(SignupDeveloperFragment mFragment) {
        this.mFragment=mFragment;
    }

    public DataLoader(SignupCompanyFragment cFragment)
    {
        this.cFragment=cFragment;
    }


    @Override
    public void onLogin() {
        if (User.getInstance().getUserType() == UserType.DEVELOPER) {
            Intent intent = new Intent(mLoginActivity, DeveloperProfileActivity.class);
            mLoginActivity.startActivity(intent);

        } else {
            Intent intent = new Intent(mLoginActivity, CompanyProfileActivity.class);
            mLoginActivity.startActivity(intent);
        }
    }
    @Override
    public void onRegistration()
    {
        Intent intent = new Intent(mFragment.getActivity(),LoginActivity.class);
        mFragment.startActivity(intent);
    }

    @Override
    public void onRegistrationCompany()
    {
        Intent intent = new Intent(cFragment.getActivity(),LoginActivity.class);
        cFragment.startActivity(intent);
    }

    @Override
    public void onFailedLogin(com.example.core.utils.LoginStatus loginStatus) {
        mLoginActivity.failed_login(loginStatus.getMessage(),loginStatus.isSuccess());
    }

    @Override
    public void onFailedRegistration(LoginStatus loginStatus) {
        mFragment.registration_failed(loginStatus.getMessage(),loginStatus.isSuccess());
    }

    @Override
    public void onFailedRegistrationCompany(LoginStatus loginStatus) {
        cFragment.registration_failed(loginStatus.getMessage(),loginStatus.isSuccess());
    }


}
