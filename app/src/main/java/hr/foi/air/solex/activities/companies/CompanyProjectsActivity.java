package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.core.utils.UserType;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.ProjectsListAdapter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenter;
import hr.foi.air.solex.presenters.CompanyProjectsPresenterImpl;

public class CompanyProjectsActivity extends DrawerActivity implements CompanyProjectsView {

    @BindView(R.id.btnAddNewProject)
    Button btnAddNewProject;

    @BindView(R.id.myProjectsList)
    ListView listViewMyProjectsList;

    CompanyProjectsPresenter mCompanyProjectsPresenter;
    private List<ProfileScreenProject> mProjectsList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_projects;
    }

    private void setLayout(int id){
        if(!User.isCurrentUser(UserType.COMPANY, id)){
            btnAddNewProject.setVisibility(View.GONE);
            setTitle(getIntent().getExtras().getString("companyName") + R.string.projects_list);
        }
    }

    private int companyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        companyId = getIntent().getExtras().getInt("companyId");

        this.mCompanyProjectsPresenter = new CompanyProjectsPresenterImpl(this);
        mCompanyProjectsPresenter.getProjects(companyId);
        setLayout(companyId);
    }

    @OnClick(R.id.btnAddNewProject)
    public void btnAddNewProjectClick() {
        Intent intent = new Intent(this, AddNewProjectActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataArrived(List<ProfileScreenProject> projects) {
        mProjectsList = projects;
        ProjectsListAdapter adapter =
                new ProjectsListAdapter(this, android.R.layout.simple_list_item_1, mProjectsList, UserType.COMPANY);

        listViewMyProjectsList.setAdapter(adapter);
        listViewMyProjectsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                ProfileScreenProject clickedProj = mProjectsList.get(pos);
                openProjectActivity(clickedProj.getId(), User.isCurrentUser(UserType.COMPANY, companyId));
            }
        });
    }

    private void openProjectActivity(int projectId, boolean owner){
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("isOwner", owner);
        intent.putExtra("projectId", projectId);
        startActivity(intent);
    }
}
