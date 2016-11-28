package hr.foi.air.solex.activities.companies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.companies.CompanyProfileActivity;

public class UpdateCompanyDataActivity extends DrawerActivity {
    @BindView(R.id.btnUpdateCompanyData)
    Button btnUpdateCompanyData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_company_data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnUpdateCompanyData)
    public void btnClick(View view){
        Intent intent = new Intent(this, CompanyProfileActivity.class);
        startActivity(intent);
        finish();
    }

}
