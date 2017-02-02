package hr.foi.air.solex.presenters.developers;

import java.util.List;

import hr.foi.air.solex.fragments.ProjectSearchResultView;
import hr.foi.air.solex.fragments.ProjectSearchView;
import hr.foi.air.solex.models.projects.Project;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.models.searched_project.SearchedProjectAndroidInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractor;
import hr.foi.air.solex.models.searched_project.SearchedProjectInteractorImpl;
import hr.foi.air.solex.models.searched_project.SearchedProjectListListener;
import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillsInteractor;
import hr.foi.air.solex.models.skills.SkillsInteractorImpl;
import hr.foi.air.solex.utils.UserType;

public class ProjectSearchPresenterImpl implements ProjectSearchPreseneter, SkillListListener, AllSkillListListener {


    private final SkillsInteractor mSkillInteractor;
    private final SearchedProjectInteractor mSearchedProjectInteractor;
    private ProjectSearchView mProjectSearchView;
    private ProjectSearchResultView mProjectSearchResultView;

    public ProjectSearchPresenterImpl(ProjectSearchView projectSearchView) {
        mProjectSearchView = projectSearchView;
        mSearchedProjectInteractor =
                new SearchedProjectInteractorImpl();
                //new SearchedProjectAndroidInteractorImpl();
        mSkillInteractor = new SkillsInteractorImpl();

        mSkillInteractor.setSkillListListener(this);
        mSkillInteractor.setAllSkillListListener(this);

    }

    /**Javi interactoru da dohvati sve kompetencije*/
    @Override
    public void getAllSkillList() {
        mSkillInteractor.getAllSkillList();
    }

    /**Javi interactoru da dohvati sve kompetencije za developera*/
    @Override
    public void getSkillList(int developerId) {
        mSkillInteractor.getSkillList(developerId, UserType.DEVELOPER);
    }


    /**Javi viewu da su sve kompetencije stigle*/
    @Override
    public void onAllSkillListCome(List<String> skills) {
        mProjectSearchView.allSkillsListArrived(skills);
    }

    /**Javi viewu da su kompetencije za developera stigle*/
    @Override
    public void onSkillListCome(List<String> skills) {
        mProjectSearchView.developerSkillsListArrived(skills);
    }

}
