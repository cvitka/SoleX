package hr.foi.air.solex.presenters;

import com.example.core.utils.UserType;
import com.example.webservice.models.mcompanies.Company;
import com.example.webservice.models.mcompanies.CompanyInteractor;
import com.example.webservice.models.mcompanies.CompanyScalarListener;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractor;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectListListener;
import com.example.webservice.models.skills.AllSkillListListener;
import com.example.webservice.models.skills.SkillListListener;
import com.example.webservice.models.skills.SkillsInteractor;
import com.example.webservice.models.skills.SkillsInteractorImpl;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProfileView;

public class CompanyProfilePresenterImpl implements CompanyProfilePresenter, CompanyScalarListener, ProfileScreenProjectListListener, SkillListListener, AllSkillListListener {
    private CompanyInteractor mCompanyInteractor;
    private CompanyProfileView mCompanyProfileView;
    private ProfileScreenProjectInteractor mProfileScreenProjectInteractor;
    private SkillsInteractor mSkillInteractor;

    @Override
    public void getHighlightedProjects(int companyId) {
        mProfileScreenProjectInteractor.getHighlightedProjectList(companyId);
    }

    public CompanyProfilePresenterImpl(CompanyProfileView companyProfileView, CompanyInteractor companyInteractor) {
        this.mCompanyInteractor = companyInteractor;
        mCompanyInteractor.setScalarListener(this);  //registriramo se kao slušatelj kod modela
        mCompanyProfileView = companyProfileView;
        mProfileScreenProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
        mSkillInteractor = new SkillsInteractorImpl();
        mSkillInteractor.setSkillListListener(this);
        mSkillInteractor.setAllSkillListListener(this);
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

    @Override
    public void getAllSkillList() {
        mSkillInteractor.getAllSkillList();
    }

    @Override
    public void getSkillList(int companyId) {
        mSkillInteractor.getSkillList(companyId, UserType.COMPANY);
    }

    @Override
    public void deleteSkill(int companyId, String skill) {
        mSkillInteractor.deleteSkill(companyId, skill, UserType.COMPANY);
    }

    @Override
    public void addSkill(int companyId, String skill) {
        mSkillInteractor.addSkill(companyId, skill, UserType.COMPANY);
    }

    @Override
    public void onSkillListCome(List<String> list) {
        mCompanyProfileView.companySkillsListArrived(list);
    }

    @Override
    public void onAllSkillListCome(List<String> list) {
        mCompanyProfileView.allSkillsListArrived(list);
    }
}
