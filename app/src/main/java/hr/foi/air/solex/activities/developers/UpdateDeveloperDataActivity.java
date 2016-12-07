package hr.foi.air.solex.activities.developers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Developers.WSRequestDeveloper;
import com.example.webservice.models.Developers.WSUpdateDeveloper;
import com.example.webservice.models.Developers.WSUpdateDeveloperListener;
import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.login_registration.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.Listeners.DeveloperDataListener;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.loaders.DLDeveloper;

import static hr.foi.air.solex.R.id.editText3;

public class UpdateDeveloperDataActivity extends DrawerActivity implements DeveloperDataListener, WSUpdateDeveloperListener {

    Developer mThisDeveloper;

    @BindView(R.id.btnUpdateDeveloperData)
    Button btnUpdateDeveloperData;

    @BindView(editText3)
    TextView txtInputNewAddress;

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
        DLDeveloper loader = new DLDeveloper(this);
        WSRequestDeveloper request = new WSRequestDeveloper(loader);
        request.getDeveloperData(id);

    }



    @OnClick(R.id.btnUpdateDeveloperData)
    public void btnClick(View view){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);

        String name= txtInputNewName.getText().toString();
        String address= txtInputNewAddress.getText().toString();
        String email = txtInputNewEmail.getText().toString();

        int id = Developer.getInstance().getId();
        Developer developer = new Developer();
        developer.setId(id);
        developer.setIme(name);
        developer.setEmail(email);
        developer.setAdresa(address);

        DLDeveloper loader = new DLDeveloper(this);
        final WSUpdateDeveloper request = new WSUpdateDeveloper(this);
        request.developerUpdateProcces(id,name,address,email);

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


    }

    @Override
    public void DataArrivedDeveloepr(Developer developer) {

        mThisDeveloper = developer;

        txtInputNewEmail.setText(mThisDeveloper.getEmail());
        txtInputNewAddress.setText(mThisDeveloper.getAdresa());
        txtInputNewName.setText(mThisDeveloper.getIme());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisDeveloper.getEmail());
    }
}
