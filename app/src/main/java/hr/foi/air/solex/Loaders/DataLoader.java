package hr.foi.air.solex.Loaders;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

import com.example.core.utils.UserType;
import com.example.webservice.WebServiceHandler;
import com.example.webservice.models.Developer;
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

    SignupDeveloperFragment mFragment;

    SignupCompanyFragment cFragment;

    public DataLoader(Activity mActivity) {
        this.mActivity = mActivity;
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
            Intent intent = new Intent(mActivity, DeveloperProfileActivity.class);
            mActivity.startActivity(intent);

        } else {
            Intent intent = new Intent(mActivity, CompanyProfileActivity.class);
            mActivity.startActivity(intent);
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
}
