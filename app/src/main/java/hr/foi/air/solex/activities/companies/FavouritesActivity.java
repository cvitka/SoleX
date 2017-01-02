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

import com.example.webservice.models.favorites.ApiFavourites;
import com.example.webservice.models.favorites.ApiFavouritesInteractorImpl;
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
    private int isFirstDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        this.mFavouritesPresenter = new FavouritesPresenterImpl(this, new ApiFavouritesInteractorImpl());
        mFavouritesPresenter.getFavourites(User.getInstance().getId());

    }

    @Override
    public void dataArrived(List<ApiFavourites> apiFavourites) {

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
    }

    public void onSelect(String id) {
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId", id);
        startActivity(intent);
    }

    @OnClick(R.id.activity_favourites_btnAddFavourites)
    void onClick() {
        final Dialog dialog = new Dialog(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View post = inflater.inflate(R.layout.favourites_search, null);
        AutoCompleteTextView  textView = (AutoCompleteTextView)post.findViewById((R.id.favouriteDialog_name));
        ImageButton bntClose = (ImageButton) post.findViewById(R.id.favouritesDialog_btnClose);
        ImageButton btnAdd = (ImageButton) post.findViewById(R.id.favouritesDialog_btnAddNewFavourite);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, mList);
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
            }
        });

    }
}
