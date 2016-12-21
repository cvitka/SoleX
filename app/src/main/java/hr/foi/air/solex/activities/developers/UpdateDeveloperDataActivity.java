package hr.foi.air.solex.activities.developers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Companies.Company;
import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.login_registration.User;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.UpdateDeveloperDataPresenter;
import hr.foi.air.solex.presenters.UpdateDeveloperDataPresenterImpl;

import static hr.foi.air.solex.R.id.editText3;

public class UpdateDeveloperDataActivity extends DrawerActivity implements UpdateDeveloperDataView {

    Developer mThisDeveloper;
    private UpdateDeveloperDataPresenter mUpdateDeveloperDataPresenter;

    @BindView(R.id.btnUpdateDeveloperData)
    Button btnUpdateDeveloperData;

    @BindView(editText3)
    TextView txtInputNewExperince;

    @BindView(R.id.editText2)
    TextView txtInputNewEmail;

    @BindView(R.id.editText)
    TextView txtInputNewName;

    ProgressDialog progressDialog;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_developer_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        int id = User.getInstance().getId();
        mUpdateDeveloperDataPresenter = new UpdateDeveloperDataPresenterImpl(this);
        String jsonMyObject = getIntent().getExtras().getString("myObject");
        mThisDeveloper = new Gson().fromJson(jsonMyObject, Developer.class);
        setDataOnLayout(mThisDeveloper);
    }

    private void setDataOnLayout(Developer dev){
        txtInputNewEmail.setText(dev.getEmail());
        txtInputNewExperince.setText(dev.getGodineIskustva());
        txtInputNewName.setText(dev.getIme());

        //View header=navigationView.getHeaderView(0);
        //TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        //textEmail.setText(dev.getEmail());
    }

    @OnClick(R.id.btnUpdateDeveloperData)
    public void btnClick(View view){

        mThisDeveloper.setIme(txtInputNewName.getText().toString());
        mThisDeveloper.setGodineIskustva(txtInputNewExperince.getText().toString());
        mThisDeveloper.setEmail(txtInputNewEmail.getText().toString());

        mUpdateDeveloperDataPresenter.updateDeveloperData(mThisDeveloper);
        progressDialog = new ProgressDialog(UpdateDeveloperDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating data...");
        progressDialog.show();
    }

    @Override
    public void onDeveloperUpdate() {
        //pozvat natrag aktivnost profila
        progressDialog.dismiss();
        Toast.makeText(this, "Profile data has been updated", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }
}
