package hr.foi.air.solex.presenters;


import com.example.webservice.models.developers.Developer;
import com.example.webservice.models.developers.DeveloperInteractor;
import com.example.webservice.models.developers.DeveloperInteractorImpl;
import com.example.webservice.models.developers.DeveloperScalarListener;

import hr.foi.air.solex.activities.developers.DeveloperProfileView;

public class DeveloperProfilePresenterImpl implements DeveloperProfilePresenter, DeveloperScalarListener {
    private DeveloperProfileView mDeveloperProfileView;
    private DeveloperInteractor mDeveloperInteractor;

    public DeveloperProfilePresenterImpl(DeveloperProfileView developerProfileView) {
        this.mDeveloperProfileView = developerProfileView;
        this.mDeveloperInteractor = new DeveloperInteractorImpl();
        mDeveloperInteractor.setScalarListener(this);
    }

    @Override
    public void getDeveloperData(int id) {
        mDeveloperInteractor.getDeveloperData(id);
    }

    @Override
    public void onDataComeDeveloper(Developer developer) {
        mDeveloperProfileView.DataArrivedDeveloper(developer);
    }
}
