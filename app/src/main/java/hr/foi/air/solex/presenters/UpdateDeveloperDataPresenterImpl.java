package hr.foi.air.solex.presenters;


import com.example.webservice.models.mdevelopers.Developer;
import com.example.webservice.models.mdevelopers.DeveloperInteractor;
import com.example.webservice.models.mdevelopers.DeveloperInteractorImpl;
import com.example.webservice.models.mdevelopers.DeveloperUpdateListener;

import hr.foi.air.solex.activities.developers.UpdateDeveloperDataView;

public class UpdateDeveloperDataPresenterImpl implements  UpdateDeveloperDataPresenter, DeveloperUpdateListener {
    private UpdateDeveloperDataView mDeveloperProfileView;
    private DeveloperInteractor mDeveloperInteractor;

    public UpdateDeveloperDataPresenterImpl(UpdateDeveloperDataView developerProfileView) {
        this.mDeveloperProfileView = developerProfileView;
        this.mDeveloperInteractor = new DeveloperInteractorImpl();
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
