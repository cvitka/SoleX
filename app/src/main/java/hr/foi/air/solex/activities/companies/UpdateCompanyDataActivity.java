package hr.foi.air.solex.activities.companies;

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

import com.example.webservice.models.Companies.CompanyModelImpl;
import com.example.webservice.models.Companies.Company;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.UpdateCompanyDataPresenter;
import hr.foi.air.solex.presenters.UpdateCompanyDataPresenterImpl;

import static hr.foi.air.solex.R.id.activity_update_company_etNewAddress;

public class UpdateCompanyDataActivity extends DrawerActivity implements  UpdateCompanyDataView {

    Company mThisCompany;
    UpdateCompanyDataPresenter mUpdateCompanyDataPresenter;

    @BindView(R.id.activity_update_company_btnUpdateData)
    Button btnUpdateCompanyData;

    @BindView(activity_update_company_etNewAddress)
    TextView txtInputNewAddress;

    @BindView(R.id.activity_update_company_etNewEmail)
    TextView txtInputNewEmail;

    @BindView(R.id.activity_update_company_etNewName)
    TextView txtInputNewName;

    ProgressDialog progressDialog;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.activity_update_company_btnChoose)
    Button btnChoseCompImg;

    @BindView(R.id.activity_update_company_iwNewImage)
    ImageView companyPicture;

    private static final int SELECT_PICTURE = 1;

    Bitmap bitmap;
    Uri selectedImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_company_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //dobavljamo bundlovani objekt
        String jsonMyObject = getIntent().getExtras().getString("myObject");
        mThisCompany = new Gson().fromJson(jsonMyObject, Company.class);
        setDataOnLayout(mThisCompany);

        this.mUpdateCompanyDataPresenter = new UpdateCompanyDataPresenterImpl(this, new CompanyModelImpl());
    }

    @OnClick(R.id.activity_update_company_btnUpdateData)
    public void btnClick(View view){
        mThisCompany.setName(txtInputNewName.getText().toString());
        mThisCompany.setAddress(txtInputNewAddress.getText().toString());
        mThisCompany.setEmail(txtInputNewEmail.getText().toString());

        mUpdateCompanyDataPresenter.updateCompanyData(mThisCompany);

        progressDialog = new ProgressDialog(UpdateCompanyDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating data...");
        progressDialog.show();
    }

    @Override
    public void updateFinished() {
        progressDialog.dismiss();
        Toast.makeText(this, "Profile data has been updated", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
    }

    public void setDataOnLayout(Company company){
        mThisCompany = company;
        txtInputNewEmail.setText(mThisCompany.getEmail());
        txtInputNewAddress.setText(mThisCompany.getAddress());
        txtInputNewName.setText(mThisCompany.getName());


        View header=navigationView.getHeaderView(0);
        TextView textEmail = (TextView)header.findViewById(R.id.textViewEmail);
        textEmail.setText(mThisCompany.getEmail());
    }

    @OnClick(R.id.activity_update_company_btnChoose)
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
                companyPicture.setImageBitmap(bitmap);
                mThisCompany.setPicture("slika");
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
