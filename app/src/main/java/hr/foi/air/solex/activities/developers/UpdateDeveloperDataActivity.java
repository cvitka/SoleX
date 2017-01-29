package hr.foi.air.solex.activities.developers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import hr.foi.air.solex.models.mdevelopers.Developer;
import hr.foi.air.solex.models.login_registration.User;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.developers.UpdateDeveloperDataPresenter;
import hr.foi.air.solex.presenters.developers.UpdateDeveloperDataPresenterImpl;

import static hr.foi.air.solex.R.id.activity_update_developer_etNewAddress;
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
    ImageView imageToUpload;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    ProgressDialog progressDialog;

    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    private int odabrano = 0;

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

    private void setDataOnLayout(Developer dev) {
        if (dev != null) {
            txtInputNewEmail.setText(dev.getEmail());
            txtInputNewExperince.setText(dev.getGodineIskustva());
            txtInputNewName.setText(dev.getIme());
            txtInputNewSurname.setText(dev.getPrezime());
            txtInputNewAddress.setText(dev.getAdresa());
            txtInputNewNumber.setText(dev.getKontaktBroj());

            String encodedImage = mThisDeveloper.getPicture();
            if (!encodedImage.isEmpty()) {
                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageToUpload.setImageBitmap(decodedByte);
            } else {
                int id = getResources().getIdentifier("hr.foi.air.solex:drawable/" + "developer_logo", null, null);
                imageToUpload.setImageResource(id);
            }

            View header = navigationView.getHeaderView(0);
            TextView textEmail = (TextView) header.findViewById(R.id.textViewEmail);
            textEmail.setText(dev.getEmail());
        }
    }

    @OnClick(R.id.activity_update_developer_btnUpdateData)
    public void btnClick(View view) {

        mThisDeveloper.setIme(txtInputNewName.getText().toString());
        mThisDeveloper.setPrezime(txtInputNewSurname.getText().toString());
        mThisDeveloper.setAdresa(txtInputNewAddress.getText().toString());
        mThisDeveloper.setEmail(txtInputNewEmail.getText().toString());
        mThisDeveloper.setKontaktBroj(txtInputNewNumber.getText().toString());
        mThisDeveloper.setGodineIskustva(txtInputNewExperince.getText().toString());

        if (odabrano == 1) {
            Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            String encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            mThisDeveloper.setPicture(encodedImage);
        }


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
        Toast.makeText(this, R.string.profile_data_update, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        intent.putExtra("developerId", User.getInstance().getId());
        startActivity(intent);
    }

    @OnClick(R.id.activity_update_developer_iwNewImage)
    public void chooseImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data != null) {
            selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
            odabrano = 1;
        }
    }

}
