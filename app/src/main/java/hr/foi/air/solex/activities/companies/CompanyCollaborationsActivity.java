package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
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
import hr.foi.air.solex.presenters.CompanyAddFavoritePresenter;
import hr.foi.air.solex.presenters.CompanyAddFavoritePresenterImpl;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenter;
import hr.foi.air.solex.presenters.CompanyCollaborationsPresenterImpl;

public class CompanyCollaborationsActivity extends DrawerActivity implements CompanyCollaborationsView,CompanyAddFavoriteView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_collaborations;
    }

    private ArrayList<String> itemsId = new ArrayList<String>();
    private String selectedProjectName;
    private String selectedDevId;

    @BindView(R.id.activity_company_collaborations_lvCollaborations)
    ListView lvCollaborations;
    CompanyCollaborationsPresenter mCompanyCollaborationsPresenter;
    CompanyAddFavoritePresenter mCompanyAddFavoritePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mCompanyCollaborationsPresenter = new CompanyCollaborationsPresenterImpl(this, new ApiCompanyCollaborationsInteractorImpl());
        mCompanyCollaborationsPresenter.getCollaborations();

        mCompanyAddFavoritePresenter = new CompanyAddFavoritePresenterImpl(this, new FavoritesInteractorImpl());
    }

    @OnItemClick(R.id.activity_company_collaborations_lvCollaborations)
    public void lvCollaborationsClick(View view) {
        Intent intent = new Intent(this, CollaborationActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDataArrived(List<ApiCompanyCollaborations> collaborationsList) {
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        for (int i = 0; i < collaborationsList.size(); i++) {
            items.add(collaborationsList.get(i).getDevName() + " " + collaborationsList.get(i).getDevSurname());
            itemsId.add(collaborationsList.get(i).getDevID());

        }
        lvCollaborations.setAdapter(itemsAdapter);
        lvCollaborations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer selectionID = Integer.parseInt(itemsId.get(i));
                mCompanyAddFavoritePresenter.addToFavorites(selectionID);

            }
        });
    }

    @Override
    public void onFavoriteAddition() {
        Toast.makeText(getApplicationContext(),"The use has been added to favorites", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onFavoriteFailure(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
