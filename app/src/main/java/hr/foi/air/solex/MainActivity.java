package hr.foi.air.solex;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //@BindView(R.id.nav_view)
    //NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
/*
        - treba maket prvo menu pa stavit novi
        navigationView.
        navigationView.inflateMenu();
*/
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }








}
