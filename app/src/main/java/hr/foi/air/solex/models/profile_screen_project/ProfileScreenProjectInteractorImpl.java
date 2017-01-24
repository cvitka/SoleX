package hr.foi.air.solex.models.profile_screen_project;

import android.util.Log;

import com.example.core.utils.UserType;
import hr.foi.air.solex.models.WebServiceCommunicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProfileScreenProjectInteractorImpl extends WebServiceCommunicator implements ProfileScreenProjectInteractor {
    private ProfileScreenProjectListListener mListListener;

    private interface WSInterfaceProject {
        @GET("dohvatiProjekte.php")
        Call<List<ProfileScreenProject>> getProjects(@Query("tipDohvacanja") String tipDohvacanja, @Query("id") int id);
    }

    public ProfileScreenProjectInteractorImpl(ProfileScreenProjectListListener listener) {
        initRetrofit();
        this.mListListener = listener;
    }

    @Override
    public void getHighlightedProjectList(int id, UserType userType) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        String tipDohvacanja = "";
        if(userType.intVal() == UserType.COMPANY.intVal())
            tipDohvacanja = "ProfileScreenProjectCompany";
        else
            tipDohvacanja = "ProfileScreenProjectDeveloper";
        Call<List<ProfileScreenProject>> call = interfaceProject.getProjects(tipDohvacanja, id);
        call.enqueue(new Callback<List<ProfileScreenProject>>() {
            @Override
            public void onResponse(Call<List<ProfileScreenProject>> call, Response<List<ProfileScreenProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProfileScreenProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }

    @Override
    public void getAllProjectList(int id) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<ProfileScreenProject>> call = interfaceProject.getProjects("AllProjectListCompany", id);
        call.enqueue(new Callback<List<ProfileScreenProject>>() {
            @Override
            public void onResponse(Call<List<ProfileScreenProject>> call, Response<List<ProfileScreenProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProfileScreenProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }
}
