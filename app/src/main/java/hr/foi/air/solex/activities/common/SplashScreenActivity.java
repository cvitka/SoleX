package hr.foi.air.solex.activities.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hr.foi.air.solex.activities.common.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
