package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewProjectActivity extends AppCompatActivity {
    @BindView(R.id.btnSaveProject)
    Button btnSaveProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSaveProject)
    public void btnSaveProjectClick(){
        Intent intent = new Intent(this, CompanyProjectsActivity.class);
        startActivity(intent);
    }
}
