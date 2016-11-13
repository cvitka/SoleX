package hr.foi.air.solex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewNeededCollaborationActivity extends DrawerActivity {
    @BindView(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    Button btnAddCollaboration;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_needed_collaboration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_new_needed_collaboration_btnAddCollaboration)
    public void btnAddCollaborationClick(){
        Intent intent = new Intent(this, ProjectManagementActivity.class);
        startActivity(intent);
    }
}
