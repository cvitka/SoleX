package hr.foi.air.solex.models.applicants;

import android.util.Log;

import java.util.List;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.utils.UserType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApplicantInteractorImpl extends WebServiceCommunicator implements ApplicantInteractor {
    private ApplicantListListener mListListener;

    private interface WSInterfaceProject {
        @GET("dohvatiAplikante.php")
        Call<List<Applicant>> getProjects(@Query("id") int id);
    }

    public ApplicantInteractorImpl(ApplicantListListener listener) {
        initRetrofit();
        this.mListListener = listener;
    }

    public ApplicantInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void setApplicantListListener(ApplicantListListener listener) {
        this.mListListener = listener;
    }

    @Override
    public void getApplicantList(int collaborationId) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<Applicant>> call = interfaceProject.getProjects(collaborationId);
        call.enqueue(new Callback<List<Applicant>>() {
            @Override
            public void onResponse(Call<List<Applicant>> call, Response<List<Applicant>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onApplicantListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Applicant>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }
}
