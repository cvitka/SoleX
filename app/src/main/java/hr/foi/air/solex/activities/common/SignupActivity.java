package hr.foi.air.solex.activities.common;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.adapters.ViewPagerAdapter;
import hr.foi.air.solex.fragments.SignupCompanyFragment;
import hr.foi.air.solex.fragments.SignupDeveloperFragment;
import me.relex.circleindicator.CircleIndicator;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.vpPager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setupViewPager(mPager);
        circleIndicator.setViewPager(mPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(SignupDeveloperFragment.newInstance());
        //on kreira dva objekta (fragmenta) i sa njima radi, onCreate u fragmentima se ne poziva dvaput samo jedanput (vrlo dobro za MVP arhitekturu jer bi nam se inace kreirali presenteri dvaput)
        adapter.addFragment(SignupCompanyFragment.newInstance());
        viewPager.setAdapter(adapter);
    }
}
