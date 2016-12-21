package hr.foi.air.solex.presenters;


import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.Developers.DeveloperInteractor;
import com.example.webservice.models.Developers.DeveloperInteractorImpl;
import com.example.webservice.models.Developers.DeveloperScalarListener;

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
