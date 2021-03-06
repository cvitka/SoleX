package hr.foi.air.solex.activities.companies;

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

import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.mcompanies.CompanyInteractorImpl;
import hr.foi.air.solex.models.mcompanies.Company;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.presenters.companies.UpdateCompanyDataPresenter;
import hr.foi.air.solex.presenters.companies.UpdateCompanyDataPresenterImpl;

import static hr.foi.air.solex.R.id.activity_update_company_etNewAddress;

public class UpdateCompanyDataActivity extends DrawerActivity implements UpdateCompanyDataView {

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

    @BindView(R.id.activity_update_company_etDirector)
    TextView txtInputNewDirector;

    @BindView(R.id.activity_update_company_etWebPage)
    TextView txtInputNewWebPage;

    @BindView(R.id.activity_update_company_etNumberOfWorkers)
    TextView txtInputNumberofWorkers;

    ProgressDialog progressDialog;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.activity_update_company_iwNewImage)
    ImageView imageToUpload;

    private static final int RESULT_LOAD_IMAGE = 1;
    private Uri selectedImage;
    private int odabrano = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_company_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //dobavljamo bundlovani objekt
        String jsonMyObject;
        if (savedInstanceState == null) {
            jsonMyObject = getIntent().getExtras().getString("myObject");
        }
        else {
            jsonMyObject = savedInstanceState.getString("myObject");
        }
        mThisCompany = new Gson().fromJson(jsonMyObject, Company.class);
        setDataOnLayout(mThisCompany);

        this.mUpdateCompanyDataPresenter = new UpdateCompanyDataPresenterImpl(this, new CompanyInteractorImpl());
    }

    @OnClick(R.id.activity_update_company_btnUpdateData)
    public void btnClick(View view) {/** ucitavanje podataka sa textviwea i prosljedivanje presenteru objekta sa podacima*/
        mThisCompany.setName(txtInputNewName.getText().toString());
        mThisCompany.setAddress(txtInputNewAddress.getText().toString());
        mThisCompany.setEmail(txtInputNewEmail.getText().toString());
        mThisCompany.setWebStranica(txtInputNewWebPage.getText().toString());
        mThisCompany.setDirektor(txtInputNewDirector.getText().toString());
        Integer brojZaposlenika = Integer.parseInt(txtInputNumberofWorkers.getText().toString());
        mThisCompany.setBrojZaposlenika(brojZaposlenika);

        if (odabrano == 1) {
            Bitmap image = ((BitmapDrawable) imageToUpload.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            String encodedImage = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
            mThisCompany.setPicture(encodedImage);
        }

        mUpdateCompanyDataPresenter.updateCompanyData(mThisCompany);

        progressDialog = new ProgressDialog(UpdateCompanyDataActivity.this,
                R.style.AppTheme_Bright_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.updating_data));
        progressDialog.show();
    }

    @Override
    public void updateFinished() {
        progressDialog.dismiss();
        Toast.makeText(this, R.string.data_updated, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        intent.putExtra("companyId", User.getInstance().getId());
        startActivity(intent);
    }

    public void setDataOnLayout(Company company) {/** postavljanje trenutnih podataka */
        if(mThisCompany != null){
            mThisCompany = company;
            txtInputNewEmail.setText(mThisCompany.getEmail());
            txtInputNewAddress.setText(mThisCompany.getAddress());
            txtInputNewName.setText(mThisCompany.getName());
            txtInputNewWebPage.setText(mThisCompany.getWebStranica());
            txtInputNewDirector.setText(mThisCompany.getDirektor());
            txtInputNumberofWorkers.setText(""+mThisCompany.getBrojZaposlenika());
            String encodedImage = mThisCompany.getPicture();

            if (!encodedImage.isEmpty()) {
                byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageToUpload.setImageBitmap(decodedByte);
            } else {
                int id = getResources().getIdentifier("hr.foi.air.solex:drawable/" + "company_logo", null, null);
                imageToUpload.setImageResource(id);
            }

            View header = navigationView.getHeaderView(0);
            TextView textEmail = (TextView) header.findViewById(R.id.textViewEmail);
            textEmail.setText(mThisCompany.getEmail());
        }

    }

    @OnClick(R.id.activity_update_company_iwNewImage)
    public void chooseImage(View view) {/**  biranje slika, ne smije biti velika limitacije na serveru */

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);/** ucitavanje slike */

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null && data != null) {

            selectedImage = data.getData();
            imageToUpload.setImageURI(selectedImage);
            odabrano = 1;
        }
    }

}
