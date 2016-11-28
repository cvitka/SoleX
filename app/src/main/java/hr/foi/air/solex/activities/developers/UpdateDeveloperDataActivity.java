package hr.foi.air.solex.activities.developers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;

public class UpdateDeveloperDataActivity extends DrawerActivity {
    @BindView(R.id.btnUpdateDeveloperData)
    Button btnUpdateDeveloperData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_developer_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.btnUpdateDeveloperData)
    public void btnClick(View view){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

}
