package hr.foi.air.solex.models.collaboration;

import android.util.Log;

import hr.foi.air.solex.models.WebServiceCommunicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiNeededCollaborationsInteractorImpl extends WebServiceCommunicator implements ApiNeededCollaborationsInteractor {

    private ApiNeededCollaborationListListener mListener;
    private NeededCollaborationDataScalarListener mNeededCollabDataScalarListener;

    private interface WSInterfaceCollaborations {
        @GET("dohvatiPotrebneSuradnje.php")
        Call<List<ApiNeededCollaborations>> getNeededCollaborations(@Query("projektId") int projektId);
        @GET("dohvatiSuradnje.php")
        Call<NeededCollaborationData> getNeededCollab(@Query("neededCollabId") int collaborationId);
    }

    public ApiNeededCollaborationsInteractorImpl() {initRetrofit();
    }

    @Override
    public void getData(int id) {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<List<ApiNeededCollaborations>> call = interfaceCollaborations.getNeededCollaborations(id);
        call.enqueue(new Callback<List<ApiNeededCollaborations>>() {
            @Override
            public void onResponse(Call<List<ApiNeededCollaborations>> call, Response<List<ApiNeededCollaborations>> response) {
                if (response.isSuccessful()) {
                    if (mListener != null) {
                        mListener.onDataListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiNeededCollaborations>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }

    @Override
    public void getNeededCollaboration(int collaborationId) {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.getNeededCollab(collaborationId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mNeededCollabDataScalarListener != null) {
                        mNeededCollabDataScalarListener.neededCollaborationDataArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }

    @Override
    public void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener) {
        mNeededCollabDataScalarListener = listener;
    }

    @Override
    public void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener) {
        mListener = neededCollaborationListListener;
    }
}
