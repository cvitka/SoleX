package hr.foi.air.solex.loaders;

import com.example.webservice.Companies.WSHandlerCompany;
import com.example.webservice.models.Company;

import java.sql.Blob;

import hr.foi.air.solex.activities.Listeners.CompanyDataListener;

/**
 * Created by tomislav on 11/30/16.
 */

public class DLCompany implements WSHandlerCompany {

    CompanyDataListener mCompanyDataListener;

    public DLCompany(CompanyDataListener companyDataListener) {
        this.mCompanyDataListener = companyDataListener;
    }

    @Override
    public void onDataCome(int id, String name, String address, String email, Blob picture) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setAddress(address);
        company.setEmail(email);
        company.setPicture(picture);
        mCompanyDataListener.DataArrived(company);
    }
}
