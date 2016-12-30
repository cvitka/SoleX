package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.CollaborationActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.adapters.CompanyCollaborationsAdapter;
import hr.foi.air.solex.adapters.DividerItemDecoration;
import hr.foi.air.solex.presenters.CompanyAddFavoritePresenter;
import hr.foi.air.solex.presenters.CompanyAddFavoritePresenterImpl;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenter;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenterImpl;

public class CompanyCollaborationsActivity extends DrawerActivity implements CompanyCollaborationsView, CompanyAddFavoriteView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_collaborations;
    }

    @BindView(R.id.activity_company_collaborations_recyclerView)
    RecyclerView recyclerView;


    CompanyCollaborationsPresenter mCompanyCollaborationsPresenter;
    CompanyAddFavoritePresenter mCompanyAddFavoritePresenter;

    private CompanyCollaborationsAdapter companyCollaborationsAdapter;
    CompanyCollaborationsAdapter.OnItemClickListener itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mCompanyCollaborationsPresenter = new CompanyCollaborationsPresenterImpl(this, new ApiCompanyCollaborationsInteractorImpl());
        mCompanyCollaborationsPresenter.getCollaborations();

        mCompanyAddFavoritePresenter = new CompanyAddFavoritePresenterImpl(this, new FavoritesInteractorImpl());
    }

    @Override
    public void onDataArrived(List<ApiCompanyCollaborations> collaborationsList) {

        companyCollaborationsAdapter = new CompanyCollaborationsAdapter(collaborationsList, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new CompanyCollaborationsAdapter(collaborationsList, new CompanyCollaborationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ApiCompanyCollaborations companyColaborations) {
                Integer selectionID = Integer.parseInt(companyColaborations.getDevID());
                mCompanyAddFavoritePresenter.addToFavorites(selectionID);
            }
        }));
    }

    @Override
    public void onFavoriteAddition() {
        Toast.makeText(getApplicationContext(), "The use has been added to favorites", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFavoriteFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
