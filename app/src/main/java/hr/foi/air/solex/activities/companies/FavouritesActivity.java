package hr.foi.air.solex.activities.companies;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import hr.foi.air.solex.models.favorites.ApiFavourites;
import hr.foi.air.solex.models.favorites.ApiFavouritesInteractorImpl;
import hr.foi.air.solex.models.favorites.FavoritesInteractorImpl;
import hr.foi.air.solex.models.login_registration.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import hr.foi.air.solex.presenters.companies.FavouritesPresenter;
import hr.foi.air.solex.presenters.companies.FavouritesPresenterImpl;

public class FavouritesActivity extends DrawerActivity implements FavouritesActivityView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favourites;
    }

    @BindView(R.id.activity_favourites_lvFavourites)
    ListView lvFavourites;

    @BindView(R.id.activity_favourites_btnAddFavourites)
    Button addFavourite;

    FavouritesPresenter mFavouritesPresenter;
    private ArrayList<Integer> itemsId = new ArrayList<Integer>();
    private ArrayList<String> mList = new ArrayList<String>();
    private ArrayList<String> mColabi = new ArrayList<String>();
    private List<ApiCompanyCollaborations> myCollabi;
    private String id;
    private String favorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mFavouritesPresenter = new FavouritesPresenterImpl(this, new ApiFavouritesInteractorImpl(),new FavoritesInteractorImpl(),new ApiCompanyCollaborationsInteractorImpl());
        /** dohvacanje favorita */
        mFavouritesPresenter.getFavourites(User.getInstance().getId());
        mFavouritesPresenter.getCollaborations();

    }

    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {/** stigli podaci popunjavanje liste i psotavljanje adaptera za listView */
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        for (int i = 0; i < apiFavourites.size(); i++) {
            items.add(apiFavourites.get(i).getDevName() + " " + apiFavourites.get(i).getDevSurname());
            itemsId.add(apiFavourites.get(i).getDevID());
            mList.add(apiFavourites.get(i).getDevName() + " " + apiFavourites.get(i).getDevSurname());

        }
        lvFavourites.setAdapter(itemsAdapter);
        lvFavourites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSelect(itemsId.get(i));
            }
        });
        lvFavourites.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mFavouritesPresenter.deleteFavorites(itemsId.get(i));
                return true;
            }
        });
    }

    @Override
    public void onCollabArrived(List<ApiCompanyCollaborations> apiCompanyCollaborationses) {
        myCollabi = apiCompanyCollaborationses;

        for (int i = 0; i < apiCompanyCollaborationses.size(); i++) {
            mColabi.add(apiCompanyCollaborationses.get(i).getDevName() + " " + apiCompanyCollaborationses.get(i).getDevSurname() + ": " + apiCompanyCollaborationses.get(i).getProjectName());
        }
    }

    /** metode iz viewa o uspjesnih, neuspesnim dodavanjima , brisanjima itd. */
    @Override
    public void onFavoriteAddition() {
        Toast.makeText(getApplicationContext(), R.string.developer_favourites, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteUpdate() {
        Toast.makeText(getApplicationContext(), R.string.developer_favourites , Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteUpdateFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteDelete() {
        Toast.makeText(getApplicationContext(), R.string.developer_favourite_delete, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteDeleteFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    public void onSelect(int id) {
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId", id);
        startActivity(intent);
    }

    @OnClick(R.id.activity_favourites_btnAddFavourites)
    void onClick() {
        final Dialog dialog = new Dialog(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View post = inflater.inflate(R.layout.favourites_search, null);
        final AutoCompleteTextView  textView = (AutoCompleteTextView)post.findViewById((R.id.favouriteDialog_name));
        ImageButton bntClose = (ImageButton) post.findViewById(R.id.favouritesDialog_btnClose);
        ImageButton btnAdd = (ImageButton) post.findViewById(R.id.favouritesDialog_btnAddNewFavourite);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, mColabi);
        textView.setAdapter(adapter);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        textView.setThreshold(2);
        dialog.setContentView(post);
        dialog.setTitle(R.string.add_new_favorite);
        dialog.show();

        bntClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skill = textView.getText().toString();

                for(String area : mColabi){
                    if(area.equals(skill)){
                       String developerID= vratiDeveloperId(skill);
                        if (vratiFavorit(skill) == null) {
                            mFavouritesPresenter.addToFavorites(Integer.parseInt(developerID));
                        } else {
                            mFavouritesPresenter.updateFavorites(Integer.parseInt(developerID));
                        }
                    }
                }
            }

        });

    }
    private String vratiDeveloperId(String skill) {
        for (int i = 0; i < myCollabi.size(); i++) {
            String developer = myCollabi.get(i).getDevName() + " " + myCollabi.get(i).getDevSurname() +": " + myCollabi.get(i).getProjectName();
            if(skill.equals (developer)){
                id = myCollabi.get(i).getDevID();
            }
        }
        return id;
    }
    private String vratiFavorit(String skill) {
        for (int i = 0; i < myCollabi.size(); i++) {
            String developer = myCollabi.get(i).getDevName() + " " + myCollabi.get(i).getDevSurname() +": " + myCollabi.get(i).getProjectName();
            if(skill.equals (developer)){
                favorit = myCollabi.get(i).getFavorit().toString();
            }
        }
        return favorit;
    }
}
