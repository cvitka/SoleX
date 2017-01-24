package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.models.mcompanies.Company;

/**
 * Created by Asus on 6.12.2016..
 */

public interface CompanySignupPresenter {
    public void tryRegister(Company company, String password);
}
