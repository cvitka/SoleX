package hr.foi.air.solex.activities;

import android.os.Bundle;

import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;

public class NeededCollaborationActivity extends DrawerActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_needed_collaboration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


    }

}
