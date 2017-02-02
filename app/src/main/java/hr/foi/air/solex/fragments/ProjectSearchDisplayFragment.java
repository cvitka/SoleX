package hr.foi.air.solex.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationActivity;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectsFeelingLuckyAdapter;
import hr.foi.air.solex.models.modularity.KeywordSearchModule;
import hr.foi.air.solex.models.modularity.LuckySearchModule;
import hr.foi.air.solex.models.modularity.SearchProjects;
import hr.foi.air.solex.models.modularity.SearchedProject;
import hr.foi.air.solex.presenters.developers.ProjectSearchDisplayPresenter;
import hr.foi.air.solex.presenters.developers.ProjectSearchDisplayPresenterImpl;

public class ProjectSearchDisplayFragment extends Fragment implements ProjectSearchDisplayView {

    @BindView(R.id.activity_search_lucky_projects_recyclerView)
    RecyclerView recyclerView;

    private SearchProjects mSearchProjects;

    private ProjectsFeelingLuckyAdapter projectsFeelingLuckyAdapter;
    ProjectsFeelingLuckyAdapter.ClickListener itemClickListener;

    ProjectSearchDisplayPresenter mProjectSearchFeelingLuckyPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjectSearchFeelingLuckyPresenter = new ProjectSearchDisplayPresenterImpl(this);
        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            /** dohvacanje podataka sa prethodnog fragmenta*/
            mSearchProjects = arguments.getParcelable(ProjectsSearchMainFragment.PROJECT_INFO);
            int module = arguments.getInt("module");
            if(module == 0){
               mProjectSearchFeelingLuckyPresenter.setInteractor(new KeywordSearchModule());
            }
            else if (module == 1){
                mProjectSearchFeelingLuckyPresenter.setInteractor(new LuckySearchModule());
            }

            mProjectSearchFeelingLuckyPresenter.getSearchedProjects(mSearchProjects.getSkills());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {  /** inflate layout */
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_projects_search_feeling_lucky, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onProjectListCome(List<SearchedProject> projects) {  /** postavljanje adaptera za recycler view*/

        projectsFeelingLuckyAdapter = new ProjectsFeelingLuckyAdapter(projects, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ProjectsFeelingLuckyAdapter(projects, new ProjectsFeelingLuckyAdapter.ClickListener() {
            @Override
            public void onItemClick(SearchedProject searchedProject) {  /** on click otvori novu aktivnost */
                Intent intent = new Intent(getActivity(), DeveloperNeededCollaborationActivity.class);
                intent.putExtra("companyName", searchedProject.getComapanyName());
                intent.putExtra("applicantsNumber", searchedProject.getApplicantsNum());
                intent.putExtra("collaborationId", searchedProject.getCollaborationId());
                startActivity(intent);
            }
        }));
    }

}
