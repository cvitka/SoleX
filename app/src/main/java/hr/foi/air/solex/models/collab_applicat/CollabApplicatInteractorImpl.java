package hr.foi.air.solex.models.collab_applicat;


import android.util.Log;

import java.util.List;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborationsInteractorImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CollabApplicatInteractorImpl extends WebServiceCommunicator implements CollabApplicatInteractor{

    /** Definiranje web service end point*/
    private interface WSInterfaceCollabApplicats {
        @GET("dohvatiApliciranja.php")
        Call<List<CollabApplicat>> getApplicatList(@Query("id") int developerId);

        @GET("dohvatiSuradnje.php")
        Call<List<CollabApplicat>> getCollabList(@Query("developerId") int developerId);
    }

    private CollabListListener mCollabListListener;
    private ApplicatListListener mApplicatListListener;

    public CollabApplicatInteractorImpl() {
        initRetrofit();
    }

    /** Dohvat kolaboracija za odredenog developera sa web servisa  */
    @Override
    public void getCollaborations(int developerId) {
        WSInterfaceCollabApplicats interfaceCollaborations = retrofit.create(WSInterfaceCollabApplicats.class);
        Call<List<CollabApplicat>> call = interfaceCollaborations.getCollabList(developerId);
        call.enqueue(new Callback<List<CollabApplicat>>() {
            @Override
            public void onResponse(Call<List<CollabApplicat>> call, Response<List<CollabApplicat>> response) {
                if (response.isSuccessful()) {
                    if (mCollabListListener != null) {
                        /**Prosljedivanje podataka*/
                        mCollabListListener.collabListArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CollabApplicat>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    /** Dohvat apliciranja za odredenog developera sa web servisa  */
    @Override
    public void getApplications(int developerId) {
        WSInterfaceCollabApplicats interfaceCollaborations = retrofit.create(WSInterfaceCollabApplicats.class);
        Call<List<CollabApplicat>> call = interfaceCollaborations.getApplicatList(developerId);
        call.enqueue(new Callback<List<CollabApplicat>>() {
            @Override
            public void onResponse(Call<List<CollabApplicat>> call, Response<List<CollabApplicat>> response) {
                if (response.isSuccessful()) {
                    if (mApplicatListListener != null) {
                        /**Prosljedivanje podataka*/
                        mApplicatListListener.applicatListArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CollabApplicat>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    /** Postavljanje listenera */
    @Override
    public void setCollabListener(CollabListListener collabListListener) {
        mCollabListListener = collabListListener;
    }

    @Override
    public void setApplicatListener(ApplicatListListener applicatListListener) {
        mApplicatListListener = applicatListListener;
    }
}
