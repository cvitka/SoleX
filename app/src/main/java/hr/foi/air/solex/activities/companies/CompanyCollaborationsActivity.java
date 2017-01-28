package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import hr.foi.air.solex.activities.common.CollaborationActivity;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import hr.foi.air.solex.models.favorites.FavoritesInteractorImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.CompanyCollaborationsAdapter;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.presenters.companies.CompanyCollaborationsPresenter;
import hr.foi.air.solex.presenters.companies.CompanyCollaborationsPresenterImpl;

public class CompanyCollaborationsActivity extends DrawerActivity implements CompanyCollaborationsView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_collaborations;
    }

    @BindView(R.id.activity_company_collaborations_recyclerView)
    RecyclerView recyclerView;

    CompanyCollaborationsPresenter mCompanyCollaborationsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mCompanyCollaborationsPresenter = new CompanyCollaborationsPresenterImpl(this, new ApiCompanyCollaborationsInteractorImpl(), new FavoritesInteractorImpl());
        mCompanyCollaborationsPresenter.getCollaborations();
    }

    @Override
    public void onDataArrived(List<ApiCompanyCollaborations> collaborationsList) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new CompanyCollaborationsAdapter(collaborationsList, new CompanyCollaborationsAdapter.ClickListener() {
            @Override
            public void onItemClick(ApiCompanyCollaborations companyCollaborations) {
                onSelect(companyCollaborations);
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

            @Override
            public void onRatingChanged(ApiCompanyCollaborations companyCollaborations, int rating) {
                mCompanyCollaborationsPresenter.rate(rating, Integer.parseInt(companyCollaborations.getDevID()), companyCollaborations.getCollaborationId());
            }
        }));
    }

    public void onSelect(ApiCompanyCollaborations collab) {
        Intent intent = new Intent(this, CollaborationActivity.class);
        intent.putExtra("collaborationName", collab.getCollaborationName());
        intent.putExtra("collaborationId", collab.getCollaborationId());
        intent.putExtra("isOwner", true);
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


    @Override
    public void onRateSucceeded() {
        Toast.makeText(getApplicationContext(), "Collaboration has been rated", Toast.LENGTH_SHORT).show();

    }
}
