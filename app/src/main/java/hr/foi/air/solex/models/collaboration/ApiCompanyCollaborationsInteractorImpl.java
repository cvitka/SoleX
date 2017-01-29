package hr.foi.air.solex.models.collaboration;

import android.util.Log;
import android.widget.Toast;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.login_registration.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiCompanyCollaborationsInteractorImpl extends WebServiceCommunicator implements ApiCompanyCollaborationsInteractor {
    private ApiCollaborationsListListener mListListener;
    private ApiCompanyCollaborationScalarListener mScalarListener;

    private interface WSInterfaceCollaborations {

        @GET("dohvatiSuradnje.php")
        Call<List<ApiCompanyCollaborations>> getCollaborations(@Query("companyID") int companyID);

        @GET("dohvatiSuradnje.php")
        Call<ApiCompanyCollaborations> getScalarCollaboration(@Query("collaborationId") int collaborationId);

    }

    public ApiCompanyCollaborationsInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void setListListener(ApiCollaborationsListListener listListener) {
        mListListener = listListener;
    }


    @Override
    public void setScalarListener(ApiCompanyCollaborationScalarListener scalarListener) {
        this.mScalarListener = scalarListener;
    }


    @Override
    public void getCollaborationScalar(int collaborationId) {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<ApiCompanyCollaborations> call = interfaceCollaborations.getScalarCollaboration(collaborationId);
        call.enqueue(new Callback<ApiCompanyCollaborations>() {
            @Override
            public void onResponse(Call<ApiCompanyCollaborations> call, Response<ApiCompanyCollaborations> response) {
                if (response.isSuccessful()) {
                    if (mScalarListener != null) {
                        mScalarListener.companyCollaborationArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiCompanyCollaborations> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void getCollaborationList() {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<List<ApiCompanyCollaborations>> call = interfaceCollaborations.getCollaborations(User.getInstance().getId());
        call.enqueue(new Callback<List<ApiCompanyCollaborations>>() {
            @Override
            public void onResponse(Call<List<ApiCompanyCollaborations>> call, Response<List<ApiCompanyCollaborations>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiCompanyCollaborations>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }



}
