package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateCompanyDataActivity extends AppCompatActivity {
    @BindView(R.id.btnUpdateCompanyData)
    Button btnUpdateCompanyData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company_data);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnUpdateCompanyData)
    public void btnClick(View view){
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
    }

}
