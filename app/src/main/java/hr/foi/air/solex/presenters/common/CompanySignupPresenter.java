package hr.foi.air.solex.presenters.common;

import hr.foi.air.solex.models.mcompanies.Company;

public interface CompanySignupPresenter {
    /**Izvrsi registraciju */
    public void tryRegister(Company company, String password);
}
