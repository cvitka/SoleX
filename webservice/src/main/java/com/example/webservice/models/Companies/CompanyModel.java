package com.example.webservice.models.Companies;

/**
 * Created by Asus on 4.12.2016..
 */

public interface CompanyModel {
    public void getCompanyData(int id);
    public void updateCompanyData(Company company);
    public void setScalarListener(CompanyScalarListener listener);
    public void setUpdateListener(CompanyUpdateListener listener);
}
