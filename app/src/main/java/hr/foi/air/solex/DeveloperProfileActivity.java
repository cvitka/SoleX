package hr.foi.air.solex;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeveloperProfileActivity extends DrawerActivity {

    @BindView(R.id.btnStartUpdateDeveloperData)
    Button btnStartUpdateDeveloperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_developer_profile;
    }


    @OnClick(R.id.btnStartUpdateDeveloperData)
    public void btnStartUpdateDeveloperDataClick(View view){
        Intent intent = new Intent(this, UpdateDeveloperDataActivity.class);
        startActivity(intent);
    }
}
