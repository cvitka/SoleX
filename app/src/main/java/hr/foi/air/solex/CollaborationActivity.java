package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollaborationActivity extends AppCompatActivity {

    @BindView(R.id.activity_collaboration_imvDeveloper)
    ImageView imvDeveloper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaboration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_collaboration_imvDeveloper)
    public void imvDeveloperClick(){
        Intent intent = new Intent(this, DeveloperProfileActivity.class);
        startActivity(intent);
    }

}
