package hr.foi.air.solex.presenters.companies;

import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.mcompanies.Company;
import hr.foi.air.solex.models.mcompanies.CompanyInteractor;
import hr.foi.air.solex.models.mcompanies.CompanyScalarListener;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;
import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyProfileView;

public class CompanyProfilePresenterImpl implements CompanyProfilePresenter, CompanyScalarListener, ProfileScreenProjectListListener, SkillListListener, AllSkillListListener {
    private CompanyInteractor mCompanyInteractor;
    private CompanyProfileView mCompanyProfileView;
    private ProfileScreenProjectInteractor mProfileScreenProjectInteractor;
    private SkillsInteractor mSkillInteractor;

    /** Reci interactoru da dohvati istaknute projekte*/
    @Override
    public void getHighlightedProjects(int companyId) {
        mProfileScreenProjectInteractor.getHighlightedProjectList(companyId, UserType.COMPANY);
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

    /**Javi interactoru da dohvati sve kompetencije */
    @Override
    public void getAllSkillList() {
        mSkillInteractor.getAllSkillList();
    }

    /**javi interactoru da dohvati sve kompetencije za poduzece */
    @Override
    public void getSkillList(int companyId) {
        mSkillInteractor.getSkillList(companyId, UserType.COMPANY);
    }

    /**Javi interactoru da obrise kompetenciju za poduzece */
    @Override
    public void deleteSkill(int companyId, String skill) {
        mSkillInteractor.deleteSkill(companyId, skill, UserType.COMPANY);
    }

    /**Javi interactoru da doda kompetenciju poduzecu*/
    @Override
    public void addSkill(int companyId, String skill) {
        mSkillInteractor.addSkill(companyId, skill, UserType.COMPANY);
    }

    /**Javi viewu da su kompetencije poduzeca dosle*/
    @Override
    public void onSkillListCome(List<String> list) {
        mCompanyProfileView.companySkillsListArrived(list);
    }

    /**Javi viewu da su sve kompetencije*/
    @Override
    public void onAllSkillListCome(List<String> list) {
        mCompanyProfileView.allSkillsListArrived(list);
    }
}
