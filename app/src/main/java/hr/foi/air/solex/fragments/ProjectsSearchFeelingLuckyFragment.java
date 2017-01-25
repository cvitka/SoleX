package hr.foi.air.solex.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.foi.air.solex.R;

public class ProjectsSearchFeelingLuckyFragment extends Fragment {

    @BindView(R.id.fragment_search_result_lvSearchResultFeelingLucky)
    ListView lvFeelingLucky;

    public interface ReturnToPreviousListener {
        void activateShakeSensor();
    }

    private ReturnToPreviousListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_projects_search_feeling_lucky, container, false);
        ButterKnife.bind(this, view);
        // mCompanySignupPresenter = new CompanySignupPresenterImpl(this);
        ArrayList<String> items = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter;
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        items.add("project 1");
        items.add("project 2");
        items.add("project 3");
        items.add("project 4dfsd");
        lvFeelingLucky.setAdapter(itemsAdapter);
        return view;
    }

    public static ProjectsSearchFeelingLuckyFragment newInstance() {
        return new ProjectsSearchFeelingLuckyFragment();
    }

    public void setListener(ReturnToPreviousListener listener) {
        mListener = listener;
    }


    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (mListener != null) {
                        mListener.activateShakeSensor();
                    }
                    return true;

                }

                return false;
            }
        });
    }

}
