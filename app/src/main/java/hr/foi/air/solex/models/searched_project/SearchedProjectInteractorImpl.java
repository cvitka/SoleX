package hr.foi.air.solex.models.searched_project;

import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.List;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.searched_project.SearchedProject;
import hr.foi.air.solex.utils.UserType;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SearchedProjectInteractorImpl extends WebServiceCommunicator implements SearchedProjectInteractor {

    private SearchedProjectListListener mListListener;

    /**Definiranje web service end point-a */
    private interface WSInterfaceProject {
        @GET("dohvatiProjekte.php")
        Call<List<SearchedProject>> getProjects(@Query("tipDohvacanja") String tipDohvacanja, @Query("percentage") int percentage, @Query("skills[]") List<String> skills);

        @GET("dohvatiProjekte.php")
        Call<List<SearchedProject>> getLucky(@Query("tipDohvacanja") String tipDohvacanja, @Query("skills[]") List<String> skills);
    }

    public SearchedProjectInteractorImpl() {
        initRetrofit();
    }

    /**Pretrazi projekte- ws filtriranje */
    @Override
    public void searchProjects(int percentage, List<String> skills) {
        String action = "ProjectSearchList";
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<SearchedProject>> call = interfaceProject.getProjects(action, percentage, skills);
        HttpUrl test = call.request().url();
        call.enqueue(new Callback<List<SearchedProject>>() {
            @Override
            public void onResponse(Call<List<SearchedProject>> call, Response<List<SearchedProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SearchedProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }
    /**Sretno trazenje-ws filtriranje */
    @Override
    public void luckySearchProjects(List<String> skills) {
        String action = "ProjectSearchLucky";
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<SearchedProject>> call = interfaceProject.getLucky(action, skills);
        call.enqueue(new Callback<List<SearchedProject>>() {
            @Override
            public void onResponse(Call<List<SearchedProject>> call, Response<List<SearchedProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SearchedProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    /**Postavi listener */
    @Override
    public void setSearchedProjectListListener(SearchedProjectListListener listListener) {
        this.mListListener = listListener;
    }
}
