package hr.foi.air.solex.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.developers.DeveloperNeededCollaborationActivity;
import hr.foi.air.solex.adapters.CompanyCollaborationsAdapter;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectSearchResultAdapter;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.searched_project.SearchProjects;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.presenters.developers.ProjectSearchResultPresenter;
import hr.foi.air.solex.presenters.developers.ProjectSearchResultPresenterImpl;

public class ProjectsSearchResultFragment extends Fragment implements ProjectSearchResultView {
    @BindView(R.id.activity_search_projects_recyclerView)
    RecyclerView recyclerView;

    private List<String> resultProjects;
    private List<SearchedProject> mProjectsList;

    private ProjectSearchResultAdapter projectSearchResultAdapter;
    ProjectSearchResultAdapter.ClickListener itemClickListener;


    private SearchProjects mSearchProjects;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectSearchResultPresenter projectSearchResultPresenter = new ProjectSearchResultPresenterImpl(this);

        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            mSearchProjects = arguments.getParcelable(ProjectsSearchMainFragment.PROJECT_INFO);
            projectSearchResultPresenter.getSearchedProjects(mSearchProjects.getPercentage(), mSearchProjects.getSkills());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_projects_search_result, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Search results");
        return view;
    }

    @Override
    public void onProjectListCome(List<SearchedProject> projects) {

        mProjectsList = projects;
        projectSearchResultAdapter = new ProjectSearchResultAdapter(projects, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ProjectSearchResultAdapter(projects, new ProjectSearchResultAdapter.ClickListener() {
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
