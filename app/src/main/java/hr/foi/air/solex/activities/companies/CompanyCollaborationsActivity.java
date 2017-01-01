package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.CompanyCollaborationsAdapter;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenter;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenterImpl;

public class CompanyCollaborationsActivity extends DrawerActivity implements CompanyCollaborationsView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_collaborations;
    }

    @BindView(R.id.activity_company_collaborations_recyclerView)
    RecyclerView recyclerView;


    CompanyCollaborationsPresenter mCompanyCollaborationsPresenter;

    private CompanyCollaborationsAdapter companyCollaborationsAdapter;
    CompanyCollaborationsAdapter.ClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mCompanyCollaborationsPresenter = new CompanyCollaborationsPresenterImpl(this, new ApiCompanyCollaborationsInteractorImpl(), new FavoritesInteractorImpl());
        mCompanyCollaborationsPresenter.getCollaborations();
    }

    @Override
    public void onDataArrived(List<ApiCompanyCollaborations> collaborationsList) {

        companyCollaborationsAdapter = new CompanyCollaborationsAdapter(collaborationsList, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new CompanyCollaborationsAdapter(collaborationsList, new CompanyCollaborationsAdapter.ClickListener() {
            @Override
            public void onItemClick(ApiCompanyCollaborations companyCollaborations) {
                Integer selectionID = Integer.parseInt(companyCollaborations.getProjectId());
                String selectedProjectId = selectionID.toString();
                onSelect(companyCollaborations.getProjectName(), selectedProjectId);
            }

            @Override
            public void onItemLongClick(ApiCompanyCollaborations companyCollaborations) {
                Integer selectionID = Integer.parseInt(companyCollaborations.getDevID());
                if (companyCollaborations.getFavorit() == null) {
                    mCompanyCollaborationsPresenter.addToFavorites(selectionID);
                } else {
                    mCompanyCollaborationsPresenter.updateFavorites(selectionID);
                }

            }
        }));
    }

    public void onSelect(String name, String id) {
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        intent.putExtra("projectName", name);
        intent.putExtra("projectId", id);
        startActivity(intent);

    }

    @Override
    public void onFavoriteAddition() {
        Toast.makeText(getApplicationContext(), "The user has been added to favorites", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFavoriteFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFavoriteUpdate() {
        Toast.makeText(getApplicationContext(), "The user has been added to favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFavoriteUpdateFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


}
