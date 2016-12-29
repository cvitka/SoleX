package com.example.webservice.models.mcompanies;

public interface CompanyInteractor {
    public void getCompanyData(int id);
    public void updateCompanyData(Company company);
    public void setScalarListener(CompanyScalarListener listener);
    public void setUpdateListener(CompanyUpdateListener listener);
}
