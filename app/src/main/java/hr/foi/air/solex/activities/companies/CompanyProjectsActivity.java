package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.webservice.models.companies.CompanyInteractorImpl;
import com.example.webservice.models.projects.ApiProject;
import com.example.webservice.models.projects.Project;
import com.example.webservice.models.projects.ProjectInteractorImpl;
import com.example.webservice.models.projects.WSResponseProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.companies.AddNewProjectActivity;
import hr.foi.air.solex.activities.companies.ProjectManagementActivity;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.adapters.ProjectsAdapter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenterImpl;
import retrofit2.Call;

public class CompanyProjectsActivity extends DrawerActivity implements CompanyProjectsView {
    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;


    CompanyProjectsPresenter mCompanyProjectsPresenter;
    private RecyclerView recyclerView;
    private ProjectsAdapter projectsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_projects;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mCompanyProjectsPresenter = new CompanyProjectsPresenterImpl(this, new ProjectInteractorImpl());
        mCompanyProjectsPresenter.getProjects();
    }

    @OnClick(R.id.btnAddNewProject)
    public void btnAddNewProjectClick() {
        Intent intent = new Intent(this, AddNewProjectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataArrived(List<ApiProject> projects) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        projectsAdapter = new ProjectsAdapter(projects);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(projectsAdapter);

    }

}
