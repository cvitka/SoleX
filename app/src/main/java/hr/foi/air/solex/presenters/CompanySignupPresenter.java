package hr.foi.air.solex.presenters;

import com.example.webservice.models.Companies.Company;

/**
 * Created by Asus on 6.12.2016..
 */

public interface CompanySignupPresenter {
    public void tryRegister(Company company, String password);
}
