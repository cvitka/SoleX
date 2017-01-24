package hr.foi.air.solex.models.signup;

import hr.foi.air.solex.models.mcompanies.Company;

/**
 * Created by Asus on 6.12.2016..
 */

public interface CompanySignupInteractor {
    public void registerCompany(Company company, String password);
}
