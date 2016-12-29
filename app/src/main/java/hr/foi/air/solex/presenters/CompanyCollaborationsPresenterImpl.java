package hr.foi.air.solex.presenters;

import com.example.webservice.models.collaboration.ApiCollaborationsListListener;
import com.example.webservice.models.collaboration.ApiCompanyCollaborations;
import com.example.webservice.models.collaboration.ApiCompanyCollaborationsInteractor;
import com.example.webservice.models.favorites.FavoritesAddListener;

import java.util.List;

import hr.foi.air.solex.activities.companies.CompanyCollaborationsView;

public class CompanyCollaborationsPresenterImpl implements CompanyCollaborationsPresenter, ApiCollaborationsListListener {

    CompanyCollaborationsView mCompanyCollaborationsView;
    ApiCompanyCollaborationsInteractor mApiCompanyCollaborationsInteractor;

    public CompanyCollaborationsPresenterImpl(CompanyCollaborationsView companyCollaborationsView, ApiCompanyCollaborationsInteractor apiCompanyCollaborationsInteractor) {
        this.mCompanyCollaborationsView = companyCollaborationsView;
        this.mApiCompanyCollaborationsInteractor = apiCompanyCollaborationsInteractor;
        mApiCompanyCollaborationsInteractor.setListListener(this);
    }

    @Override
    public void getCollaborations() {

        mApiCompanyCollaborationsInteractor.getCollaborationList();
    }


    @Override
    public void onProjectListCome(List<ApiCompanyCollaborations> collaborations) {
        mCompanyCollaborationsView.onDataArrived(collaborations);
    }

}
