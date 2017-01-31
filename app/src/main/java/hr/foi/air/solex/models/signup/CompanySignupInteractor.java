package hr.foi.air.solex.models.signup;

import hr.foi.air.solex.models.mcompanies.Company;

/**Registriraj poduzece */
public interface CompanySignupInteractor {
    public void registerCompany(Company company, String password);
}
