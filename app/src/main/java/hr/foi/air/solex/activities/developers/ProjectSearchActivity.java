package hr.foi.air.solex.activities.developers;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.DrawerActivity;
import hr.foi.air.solex.fragments.ProjectsSearchMainFragment;

public class ProjectSearchActivity extends DrawerActivity {

    ProjectsSearchMainFragment projectsSearchMainFragment;

    @BindView(R.id.content_frame)
    RelativeLayout frameLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        /** ucitavanje fragmenta */
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, projectsSearchMainFragment.newInstance());
        fragmentTransaction.commit();

    }
}
