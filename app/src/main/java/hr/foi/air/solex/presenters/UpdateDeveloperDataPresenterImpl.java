package hr.foi.air.solex.presenters;


import com.example.webservice.models.Developers.Developer;
import com.example.webservice.models.Developers.DeveloperInteractor;
import com.example.webservice.models.Developers.DeveloperInteractorImpl;
import com.example.webservice.models.Developers.DeveloperUpdateListener;

import hr.foi.air.solex.activities.developers.UpdateDeveloperDataView;

public class UpdateDeveloperDataPresenterImpl implements  UpdateDeveloperDataPresenter, DeveloperUpdateListener {
    private UpdateDeveloperDataView mDeveloperProfileView;
    private DeveloperInteractor mDeveloperInteractor;

    public UpdateDeveloperDataPresenterImpl(UpdateDeveloperDataView developerProfileView) {
        this.mDeveloperProfileView = developerProfileView;
        this.mDeveloperInteractor = new DeveloperInteractorImpl();
        mDeveloperInteractor.setUpdateListener(this);
    }

    @Override
    public void updateDeveloperData(Developer developer) {
        mDeveloperInteractor.updateDeveloperData(developer.getId(), developer.getIme(),developer.getPrezime(), developer.getAdresa(), developer.getEmail(),developer.getKontaktBroj(),developer.getGodineIskustva(),developer.getPicture());
    }

    @Override
    public void onDeveloperUpdate() {
        mDeveloperProfileView.onDeveloperUpdate();
    }
}
