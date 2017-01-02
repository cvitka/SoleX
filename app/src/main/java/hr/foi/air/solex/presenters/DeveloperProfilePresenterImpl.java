package hr.foi.air.solex.presenters;


import com.example.core.utils.UserType;
import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.mdevelopers.DeveloperInteractor;
import com.example.webservice.models.mdevelopers.DeveloperInteractorImpl;
import com.example.webservice.models.mdevelopers.DeveloperScalarListener;
import com.example.webservice.models.profile_screen_project.ProfileScreenProject;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractor;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectInteractorImpl;
import com.example.webservice.models.profile_screen_project.ProfileScreenProjectListListener;
import com.example.webservice.models.skills.AllSkillListListener;
import com.example.webservice.models.skills.SkillListListener;
import com.example.webservice.models.skills.SkillsInteractor;
import com.example.webservice.models.skills.SkillsInteractorImpl;

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

    @Override
    public void getDeveloperData(int id) {
        mDeveloperInteractor.getDeveloperData(id);
    }

    @Override
    public void onDataComeDeveloper(Developer developer) {
        mDeveloperProfileView.DataArrivedDeveloper(developer);
    }

    @Override
    public void onProjectListCome(List<ProfileScreenProject> projects) {
        mDeveloperProfileView.HighlihtedProjectsArrived(projects);
    }

    @Override
    public void onAllSkillListCome(List<String> skills) {
        mDeveloperProfileView.allSkillsListArrived(skills);
    }

    @Override
    public void onSkillListCome(List<String> skills) {
        mDeveloperProfileView.developerSkillsListArrived(skills);
    }

    @Override
    public void getHighlightedProjects(int developerId) {
        mProfileScreenProjectInteractor.getHighlightedProjectList(developerId, UserType.DEVELOPER);
    }

    @Override
    public void getAllSkillList() {
        mSkillInteractor.getAllSkillList();
    }

    @Override
    public void getSkillList(int developerId) {
        mSkillInteractor.getSkillList(developerId, UserType.DEVELOPER);
    }

    @Override
    public void deleteSkill(int developerId, String skill) {
        mSkillInteractor.deleteSkill(developerId, skill, UserType.DEVELOPER);
    }

    @Override
    public void addSkill(int developerId, String skill) {
        mSkillInteractor.addSkill(developerId, skill, UserType.DEVELOPER);
    }
}
