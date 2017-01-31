package hr.foi.air.solex.models.mcompanies;

public interface CompanyInteractor {
    /**Dohvati podatke za kompaniju */
    public void getCompanyData(int id);
    /**Azuriraj podatke kompanije */
    public void updateCompanyData(Company company);
    /**Javi da su podatci dosli */
    public void setScalarListener(CompanyScalarListener listener);
    /**Javi da je azuriranje uspjesno */
    public void setUpdateListener(CompanyUpdateListener listener);
}
