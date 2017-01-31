package hr.foi.air.solex.presenters.developers;


import hr.foi.air.solex.presenters.developers.DeveloperProfilePresenter;
import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.mdevelopers.Developer;
import hr.foi.air.solex.models.mdevelopers.DeveloperInteractor;
import hr.foi.air.solex.models.mdevelopers.DeveloperInteractorImpl;
import hr.foi.air.solex.models.mdevelopers.DeveloperScalarListener;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractor;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProjectListListener;
import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;

import java.util.List;

import hr.foi.air.solex.activities.developers.DeveloperProfileView;

public class DeveloperProfilePresenterImpl implements DeveloperProfilePresenter, DeveloperScalarListener, ProfileScreenProjectListListener, SkillListListener, AllSkillListListener {
    private final ProfileScreenProjectInteractor mProfileScreenProjectInteractor;
    private final SkillsInteractor mSkillInteractor;
    private DeveloperProfileView mDeveloperProfileView;
    private DeveloperInteractor mDeveloperInteractor;

    public DeveloperProfilePresenterImpl(DeveloperProfileView developerProfileView) {
        this.mDeveloperProfileView = developerProfileView;
        this.mDeveloperInteractor = new DeveloperInteractorImpl();
        mDeveloperInteractor.setScalarListener(this);
        mProfileScreenProjectInteractor = new ProfileScreenProjectInteractorImpl(this);
        mSkillInteractor = new SkillsInteractorImpl();
        mSkillInteractor.setSkillListListener(this);
        mSkillInteractor.setAllSkillListListener(this);
    }

    /**Javi interactoru da dohvati podatke o developeru*/
    @Override
    public void getDeveloperData(int id) {
        mDeveloperInteractor.getDeveloperData(id);
    }

    /**Javi viewu da su stigli podatci o developeru*/
    @Override
    public void onDataComeDeveloper(Developer developer) {
        mDeveloperProfileView.DataArrivedDeveloper(developer);
    }

    /**Javi viewu da je lista istaknutih projekata stigla*/
    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mDeveloperProfileView.HighlihtedProjectsArrived(projects);
    }

    /**Javi viewu da je lista svih kompetencija stigila*/
    @Override
    public void onAllSkillListCome(List<String> skills) {
        mDeveloperProfileView.allSkillsListArrived(skills);
    }

    /**Javi viewu da da je lista kompetenija stigla*/
    @Override
    public void onSkillListCome(List<String> skills) {
        mDeveloperProfileView.developerSkillsListArrived(skills);
    }

    /**Javi interactoru da dohvati istaknute projekte*/
    @Override
    public void getHighlightedProjects(int developerId) {
        mProfileScreenProjectInteractor.getHighlightedProjectList(developerId, UserType.DEVELOPER);
    }

    /**Javi interactoru da dohvati sve kompetencije */
    @Override
    public void getAllSkillList() {
        mSkillInteractor.getAllSkillList();
    }

    /**Javi interactoru da dohvati kompetencije za developera*/
    @Override
    public void getSkillList(int developerId) {
        mSkillInteractor.getSkillList(developerId, UserType.DEVELOPER);
    }

    /**Javi interactoru da ukloni kompetenciju*/
    @Override
    public void deleteSkill(int developerId, String skill) {
        mSkillInteractor.deleteSkill(developerId, skill, UserType.DEVELOPER);
    }

    /**Javi interactoru da doda kompetenciju */
    @Override
    public void addSkill(int developerId, String skill) {
        mSkillInteractor.addSkill(developerId, skill, UserType.DEVELOPER);
    }
}
