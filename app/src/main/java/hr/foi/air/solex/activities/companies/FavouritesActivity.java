package hr.foi.air.solex.activities.companies;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.ApiFavouritesInteractorImpl;
import com.example.webservice.models.favorites.FavoritesInteractorImpl;
import com.example.webservice.models.login_registration.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;
import hr.foi.air.solex.presenters.FavouritesPresenter;
import hr.foi.air.solex.presenters.FavouritesPresenterImpl;

public class FavouritesActivity extends DrawerActivity implements FavouritesActivityView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favourites;
    }

    @BindView(R.id.activity_favourites_lvFavourites)
    ListView lvFavourites;

    @BindView(R.id.activity_favourites_btnAddFavourites)
    Button addFavourite;



    AlertDialog alertDialog;
    FavouritesPresenter mFavouritesPresenter;
    private ArrayList<String> itemsId = new ArrayList<String>();
    private ArrayList<String> mList = new ArrayList<String>();
    private ArrayList<String> mColabi = new ArrayList<String>();
    private List<ApiFavourites> mFavoriti;
    private List<ApiCompanyCollaborations> myCollabi;
    private int isFirstDialog;
    private String id;
    private String favorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mFavouritesPresenter = new FavouritesPresenterImpl(this, new ApiFavouritesInteractorImpl(),new FavoritesInteractorImpl(),new ApiCompanyCollaborationsInteractorImpl());
        mFavouritesPresenter.getFavourites(User.getInstance().getId());
        mFavouritesPresenter.getCollaborations();

    }

    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {
        mFavoriti = apiFavourites;

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
                mFavouritesPresenter.deleteFavorites(Integer.parseInt(itemsId.get(i)));
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

    @Override
    public void onFavoriteAddition() {
        Toast.makeText(getApplicationContext(), "The developer has been added to favorites", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getApplicationContext(), "The developer has been added to favorites", Toast.LENGTH_LONG).show();
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
        Toast.makeText(getApplicationContext(), "The developer has been deleted from favorites", Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onFavoriteDeleteFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        finish();
        startActivity(getIntent());
    }

    public void onSelect(String id) {
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId", Integer.parseInt(id));
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
        dialog.setTitle("Add new Favorite");
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
                //budi here u go rock our world
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
