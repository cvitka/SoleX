package hr.foi.air.solex.presenters.developers;


import hr.foi.air.solex.models.mdevelopers.Developer;
import hr.foi.air.solex.models.mdevelopers.DeveloperInteractor;
import hr.foi.air.solex.models.mdevelopers.DeveloperInteractorImpl;
import hr.foi.air.solex.models.mdevelopers.DeveloperUpdateListener;

import hr.foi.air.solex.activities.developers.UpdateDeveloperDataView;
import hr.foi.air.solex.presenters.developers.UpdateDeveloperDataPresenter;

public class UpdateDeveloperDataPresenterImpl implements UpdateDeveloperDataPresenter, DeveloperUpdateListener {
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
