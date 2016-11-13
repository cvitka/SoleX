package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class FavouritesActivity extends DrawerActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_favourites;
    }
    @BindView(R.id.activity_favourites_lvFavourites)
    ListView lvFavourites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        items.add("developer 1");
        items.add("developer 2");
        items.add("developer 3");
        items.add("developer 4");
        lvFavourites.setAdapter(itemsAdapter);
    }

    @OnItemClick(R.id.activity_favourites_lvFavourites)
    public void lvFavouritesClick(View view){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }
}
