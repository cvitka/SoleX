package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.mcompanies.CompanyInteractor;
import hr.foi.air.solex.models.mcompanies.CompanyUpdateListener;

import hr.foi.air.solex.activities.companies.UpdateCompanyDataView;


public class UpdateCompanyDataPresenterImpl implements UpdateCompanyDataPresenter, CompanyUpdateListener {
    CompanyInteractor mCompanyInteractor;
    UpdateCompanyDataView mUpdateCompanyDataView;

    public UpdateCompanyDataPresenterImpl(UpdateCompanyDataView updateCompanyDataView, CompanyInteractor companyInteractor) {
        this.mCompanyInteractor = companyInteractor;
        this.mUpdateCompanyDataView = updateCompanyDataView;
        mCompanyInteractor.setUpdateListener(this);  //preplaćujemo se kao slušatelj za update akciju kod modela
    }
    //metoda iz UpdateCompanyDataPresenter interfacea, poziva ju UpdateCompanyDataActivity
    @Override
    public void updateCompanyData(Company company) {
        mCompanyInteractor.updateCompanyData(company);
    }
    //metoda iz CompanyUpdateListener, poziva ju model
    @Override
    public void onCompanyUpdate() {
        mUpdateCompanyDataView.updateFinished();
    }
}
