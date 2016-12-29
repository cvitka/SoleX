package com.example.webservice.models.signup;

import com.example.webservice.models.mcompanies.Company;

/**
 * Created by Asus on 6.12.2016..
 */

public interface CompanySignupModel {
    public void registerCompany(Company company, String password);
}
