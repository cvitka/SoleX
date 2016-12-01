package hr.foi.air.solex.loaders;

import android.content.Intent;

import com.example.webservice.WSHandlerCompany;

import hr.foi.air.solex.activities.companies.CompanyProfileActivity;
import hr.foi.air.solex.activities.companies.UpdateCompanyDataActivity;

/**
 * Created by tomislav on 11/30/16.
 */

public class DLCompany implements WSHandlerCompany {

    UpdateCompanyDataActivity mCompanyActivity;

    public DLCompany(UpdateCompanyDataActivity updateActivity) {
        this.mCompanyActivity = updateActivity;
    }

    @Override
    public void onCompanyUpdate() {
        Intent intent = new Intent(mCompanyActivity, CompanyProfileActivity.class);
        mCompanyActivity.startActivity(intent);

    }
}
