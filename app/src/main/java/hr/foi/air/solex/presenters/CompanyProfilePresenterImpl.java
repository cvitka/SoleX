package hr.foi.air.solex.presenters;

import com.example.webservice.models.mcompanies.Company;
import com.example.webservice.models.mcompanies.CompanyInteractor;
import com.example.webservice.models.mcompanies.CompanyScalarListener;

import hr.foi.air.solex.activities.companies.CompanyProfileView;

/**
 * Created by Asus on 4.12.2016..
 */

public class CompanyProfilePresenterImpl implements CompanyProfilePresenter, CompanyScalarListener{
    private CompanyInteractor mCompanyInteractor;
    private CompanyProfileView mCompanyProfileView;

    public CompanyProfilePresenterImpl(CompanyProfileView companyProfileView, CompanyInteractor companyInteractor) {
        this.mCompanyInteractor = companyInteractor;
        mCompanyInteractor.setScalarListener(this);  //registriramo se kao slušatelj kod modela
        mCompanyProfileView = companyProfileView;
    }

    //metoda iz CompanyProfilePresenter interfacea, ovu metodu poziva CompanyProfileActivity
    @Override
    public void getCompany(int companyId) {
        mCompanyInteractor.getCompanyData(companyId);
    }

    //metoda iz CompanyScalarListener interfacea, ovu metodu poziva mCompanyInteractor kada stignu podaci
    @Override
    public void onDataCome(Company company) {
        mCompanyProfileView.DataArrived(company);
    }
}
