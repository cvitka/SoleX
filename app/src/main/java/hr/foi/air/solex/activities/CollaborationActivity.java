package hr.foi.air.solex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.activities.developers.DeveloperProfileActivity;

public class CollaborationActivity extends DrawerActivity {

    @BindView(R.id.activity_collaboration_imvDeveloper)
    ImageView imvDeveloper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_collaboration_imvDeveloper)
    public void imvDeveloperClick(){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collaboration;
    }
}
