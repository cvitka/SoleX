package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateDeveloperDataActivity extends AppCompatActivity {
    @BindView(R.id.btnUpdateDeveloperData)
    Button btnUpdateDeveloperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_developer_data);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.btnUpdateDeveloperData)
    public void btnClick(View view){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

}
