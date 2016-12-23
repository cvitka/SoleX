package hr.foi.air.solex.activities.developers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.login_registration.User;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.UpdateDeveloperDataPresenter;
import hr.foi.air.solex.presenters.UpdateDeveloperDataPresenterImpl;

import static hr.foi.air.solex.R.id.activity_update_company_etNewAddress;
import static hr.foi.air.solex.R.id.activity_update_developer_etNewAddress;
import static hr.foi.air.solex.R.id.activity_update_developer_etNewEmail;
import static hr.foi.air.solex.R.id.activity_update_developer_etNewSurname;
import static hr.foi.air.solex.R.id.activity_update_developer_etNewYears;

public class UpdateDeveloperDataActivity extends DrawerActivity implements UpdateDeveloperDataView {

    Developer mThisDeveloper;
    private UpdateDeveloperDataPresenter mUpdateDeveloperDataPresenter;

    @BindView(R.id.activity_update_developer_btnUpdateData)
    Button btnUpdateDeveloperData;

    @BindView(R.id.activity_update_developer_etNewName)
    TextView txtInputNewName;

    @BindView(activity_update_developer_etNewSurname)
    TextView txtInputNewSurname;

    @BindView(activity_update_developer_etNewAddress)
    TextView txtInputNewAddress;

    @BindView(R.id.activity_update_developer_etNewEmail)
    TextView txtInputNewEmail;

    @BindView(R.id.activity_update_developer_etNewNumber)
    TextView txtInputNewNumber;

    @BindView(activity_update_developer_etNewYears)
    TextView txtInputNewExperince;

    @BindView(R.id.activity_update_developer_iwNewImage)
    ImageView developerPicture;

    @BindView(R.id.activity_update_developer_btnChoose)
    Button btnChoseDevImg;

    ProgressDialog progressDialog;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    Bitmap bitmap;
    Uri selectedImage;
    private static final int SELECT_PICTURE = 1;

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
        txtInputNewSurname.setText(dev.getPrezime());
        txtInputNewAddress.setText(dev.getAdresa());
        txtInputNewNumber.setText(dev.getKontaktBroj());

        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(dev.getEmail());
    }

    @OnClick(R.id.activity_update_developer_btnUpdateData)
    public void btnClick(View view){

        mThisDeveloper.setIme(txtInputNewName.getText().toString());
        mThisDeveloper.setPrezime(txtInputNewSurname.getText().toString());
        mThisDeveloper.setAdresa(txtInputNewAddress.getText().toString());
        mThisDeveloper.setEmail(txtInputNewEmail.getText().toString());
        mThisDeveloper.setKontaktBroj(txtInputNewNumber.getText().toString());
        mThisDeveloper.setGodineIskustva(txtInputNewExperince.getText().toString());

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

    @OnClick(R.id.activity_update_developer_btnChoose)
    public void chooseImage(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                developerPicture.setImageBitmap(bitmap);
                mThisDeveloper.setSlika("slika");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
