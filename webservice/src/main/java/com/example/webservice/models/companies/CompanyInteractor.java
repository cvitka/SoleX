package com.example.webservice.models.companies;

public interface CompanyInteractor {
    public void getCompanyData(int id);
    public void updateCompanyData(Company company);
    public void setScalarListener(CompanyScalarListener listener);
    public void setUpdateListener(CompanyUpdateListener listener);
}
