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

    ApiNeededCollaborationListListener mListener;

    private interface WSInterfaceCollaborations {
        @GET("dohvatiPotrebneSuradnje.php")
        Call<List<ApiNeededCollaborations>> getNeededCollaborations(@Query("projektId") int projektId);
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
    public void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener) {
        mListener = neededCollaborationListListener;
    }
}
