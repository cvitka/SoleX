package hr.foi.air.solex.presenters;

import com.example.webservice.models.Companies.Company;
import com.example.webservice.models.Companies.CompanyModel;
import com.example.webservice.models.Companies.CompanyScalarListener;
import com.example.webservice.models.Companies.CompanyUpdateListener;

import hr.foi.air.solex.activities.companies.UpdateCompanyDataView;

/**
 * Created by Asus on 4.12.2016..
 */

public class UpdateCompanyDataPresenterImpl implements UpdateCompanyDataPresenter, CompanyUpdateListener {
    CompanyModel mCompanyModel;
    UpdateCompanyDataView mUpdateCompanyDataView;

    public UpdateCompanyDataPresenterImpl(UpdateCompanyDataView updateCompanyDataView, CompanyModel companyModel) {
        this.mCompanyModel = companyModel;
        this.mUpdateCompanyDataView = updateCompanyDataView;
        mCompanyModel.setUpdateListener(this);  //preplaćujemo se kao slušatelj za update akciju kod modela
    }
    //metoda iz UpdateCompanyDataPresenter interfacea, poziva ju UpdateCompanyDataActivity
    @Override
    public void updateCompanyData(Company company) {
        mCompanyModel.updateCompanyData(company);
    }
    //metoda iz CompanyUpdateListener, poziva ju model
    @Override
    public void onCompanyUpdate() {
        mUpdateCompanyDataView.updateFinished();
    }
}
