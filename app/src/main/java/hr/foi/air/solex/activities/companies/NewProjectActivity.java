package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.CompanyProjectsActivity;
import hr.foi.air.solex.activities.common.DrawerActivity;

public class NewProjectActivity extends DrawerActivity {
    @BindView(R.id.btnSaveProject)
    Button btnSaveProject;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_project;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSaveProject)
    public void btnSaveProjectClick(){
        Intent intent = new Intent(this, CompanyProjectsActivity.class);
        startActivity(intent);
    }
}
