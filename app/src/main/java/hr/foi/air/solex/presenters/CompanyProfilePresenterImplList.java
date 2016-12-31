package hr.foi.air.solex.presenters;

import com.example.webservice.models.mcompanies.Company;
import com.example.webservice.models.mcompanies.CompanyInteractor;
import com.example.webservice.models.mcompanies.CompanyScalarListener;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractor;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectListListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProfileView;

public class CompanyProfilePresenterImplList implements CompanyProfilePresenter, CompanyScalarListener, ProfileScreenProjectListListener {
    private CompanyInteractor mCompanyInteractor;
    private CompanyProfileView mCompanyProfileView;
    private ProfileScreenProjectInteractor mProfileScreenProjectInteractor;

    @Override
    public void getHighlightedProjects(int companyId) {
        mProfileScreenProjectInteractor.getHighlightedProjectList(companyId);
    }

    public CompanyProfilePresenterImplList(CompanyProfileView companyProfileView, CompanyInteractor companyInteractor) {
        this.mCompanyInteractor = companyInteractor;
        mCompanyInteractor.setScalarListener(this);  //registriramo se kao slušatelj kod modela
        mCompanyProfileView = companyProfileView;
        mProfileScreenProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
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

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mCompanyProfileView.HighlihtedProjectsArrived(projects);
    }
}
