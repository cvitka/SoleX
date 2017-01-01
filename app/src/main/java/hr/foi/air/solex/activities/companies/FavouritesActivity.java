package hr.foi.air.solex.activities.companies;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

       /* final ArrayList<ApiFavourites> lista = new ArrayList<>(apiFavourites);
        CustomAdapterFavourites adapter = new CustomAdapterFavourites(this,lista);
        lvFavourites.setAdapter(adapter);*/


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

    public void onSelect(String id){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId",id);
        startActivity(intent);
    }

    @OnClick(R.id.activity_favourites_btnAddFavourites)
    void onClick(){
/*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.ThemeOverlay_Material_Dark));

        String[] favorit = new String[mList.size()];
        favorit = mList.toArray(favorit);
         //String[] favorit = {"Cheese", "Pepperoni", "Black Olives"};




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.test_list_item, favorit);

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View search_View = inflater.inflate(R.layout.favourites_search, null);
        final AutoCompleteTextView myAutoComplete = (AutoCompleteTextView) search_View.findViewById(R.id.favoruritesName32hhh);

        myAutoComplete.setAdapter(adapter);
        myAutoComplete.setThreshold(2);

        alertDialogBuilder.setView(search_View);
        alertDialogBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isFirstDialog = 0;
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        if (mList.isEmpty() )
        {
            isFirstDialog = 0;
        }
        else alertDialog.show();

        Button add = (Button)search_View.findViewById(R.id.btn_yes);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Profile data has been updated", Toast.LENGTH_LONG).show();
            }
        });
*/

    }
}
