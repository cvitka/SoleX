package hr.foi.air.solex.Loaders;

import android.app.Activity;
import android.content.Intent;

import com.example.core.utils.UserType;
import com.example.webservice.WebServiceHandler;
import com.example.webservice.models.User;

import hr.foi.air.solex.CompanyProfileActivity;
import hr.foi.air.solex.DeveloperProfileActivity;

/**
 * Created by cvitka on 11.11.16..
 */

public class DataLoader implements WebServiceHandler{

    Activity mActivity;

    public DataLoader(Activity mActivity) {
        this.mActivity = mActivity;
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
}
