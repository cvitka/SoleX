package hr.foi.air.solex.presenters;

import com.example.webservice.models.Companies.Company;
import com.example.webservice.models.Companies.CompanyModel;
import com.example.webservice.models.Companies.CompanyScalarListener;

import hr.foi.air.solex.activities.companies.CompanyProfileView;

/**
 * Created by Asus on 4.12.2016..
 */

public class CompanyProfilePresenterImpl implements CompanyProfilePresenter, CompanyScalarListener{
    private CompanyModel mCompanyModel;
    private CompanyProfileView mCompanyProfileView;

    public CompanyProfilePresenterImpl(CompanyProfileView companyProfileView, CompanyModel companyModel) {
        this.mCompanyModel = companyModel;
        mCompanyModel.setScalarListener(this);  //registriramo se kao slušatelj kod modela
        mCompanyProfileView = companyProfileView;
    }
    //metoda iz CompanyProfilePresenter interfacea, ovu metodu poziva CompanyProfileActivity
    @Override
    public void getCompany(int companyId) {
        mCompanyModel.getCompanyData(companyId);
    }
    //metoda iz CompanyScalarListener interfacea, ovu metodu poziva mCompanyModel kada stignu podaci
    @Override
    public void onDataCome(Company company) {
        mCompanyProfileView.DataArrived(company);
    }
}
