package hr.foi.air.solex.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationActivity;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectsFeelingLuckyAdapter;
import hr.foi.air.solex.models.searched_project.SearchProjects;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.presenters.developers.ProjectSearchFeelingLuckyPresenter;
import hr.foi.air.solex.presenters.developers.ProjectSearchFeelingLuckyPresenterImpl;

public class ProjectsSearchFeelingLuckyFragment extends Fragment implements ProjectsSearchFeelingLuckyView {

    @BindView(R.id.activity_search_lucky_projects_recyclerView)
    RecyclerView recyclerView;

    private SearchProjects mSearchProjects;

    private ProjectsFeelingLuckyAdapter projectsFeelingLuckyAdapter;
    ProjectsFeelingLuckyAdapter.ClickListener itemClickListener;

    ProjectSearchFeelingLuckyPresenter mProjectSearchFeelingLuckyPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProjectSearchFeelingLuckyPresenter = new ProjectSearchFeelingLuckyPresenterImpl(this);
        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            mSearchProjects = arguments.getParcelable(ProjectsSearchMainFragment.PROJECT_INFO);
            mProjectSearchFeelingLuckyPresenter.getSearchedProjects(mSearchProjects.getSkills());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_projects_search_feeling_lucky, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onProjectListCome(List<SearchedProject> projects) {
        ArrayList<SearchedProject> random = new ArrayList();
        random.add(projects.get(new Random().nextInt(projects.size())));
/*      // napravio sam prvo tri, ali nekad mi ne da unique, prema uputama pise 1(i ovako nece bit u appu)
        List<SearchedProject> uniqueProjects = new ArrayList<>(new LinkedHashSet<>(random));
        if (projects.size() == 1) {
            random.add(projects.get(new Random().nextInt(projects.size())));
        } else if (projects.size() == 2) {
            random.add(projects.get(new Random().nextInt(projects.size())));
            random.add(projects.get(new Random().nextInt(projects.size())));
            random.clear();
            random.addAll(uniqueProjects);
            while (uniqueProjects.size() != 2) {
                uniqueProjects.add(projects.get(new Random().nextInt(projects.size())));
            }
        } else if (projects.size() > 3) {
            random.add(projects.get(new Random().nextInt(projects.size())));
            random.add(projects.get(new Random().nextInt(projects.size())));
            random.add(projects.get(new Random().nextInt(projects.size())));

            random.clear();
            random.addAll(uniqueProjects);
            while (uniqueProjects.size() != 3) {
                uniqueProjects.add(projects.get(new Random().nextInt(projects.size())));
            }
        }*/
        projectsFeelingLuckyAdapter = new ProjectsFeelingLuckyAdapter(random, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ProjectsFeelingLuckyAdapter(random, new ProjectsFeelingLuckyAdapter.ClickListener() {
            @Override
            public void onItemClick(SearchedProject searchedProject) {
                Intent intent = new Intent(getActivity(), DeveloperNeededCollaborationActivity.class);
                intent.putExtra("companyName", searchedProject.getComapanyName());
                intent.putExtra("applicantsNumber", searchedProject.getApplicantsNum());
                intent.putExtra("collaborationId", searchedProject.getCollaborationId());
                startActivity(intent);
            }
        }));
    }

}
