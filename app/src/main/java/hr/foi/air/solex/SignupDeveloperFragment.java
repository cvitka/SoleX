package hr.foi.air.solex;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class SignupDeveloperFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_signup_developer, container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public SignupDeveloperFragment() {
    }

    public static SignupDeveloperFragment newInstance(){
        SignupDeveloperFragment signupDeveloperFragment = new SignupDeveloperFragment();
        return signupDeveloperFragment;
    }

    @OnClick(R.id.link_login_developer)
    public void click_login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
