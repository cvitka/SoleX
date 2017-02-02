package hr.foi.air.solex.models.modularity;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.foi.air.solex.models.WebServiceCommunicator;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class KeywordSearchModule extends WebServiceCommunicator implements SearchedProjectInteractor {
    private SearchedProjectListListener mListListener;

    public KeywordSearchModule() {
        initRetrofit();
    }

    /**Definiranje web service end point-a */
    private interface WSInterfaceProject {
        @GET("Module2ApiProjects.php")
        Call<List<SearchedProject>> getProjects(@Query("skills[]") List<String> skills);
    }

    /**Pretrazi projekte po kompetencijama i postotku podudarnosti-android filtriranje */
    @Override
    public void searchProjects(final List<String> skills) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<SearchedProject>> call = interfaceProject.getProjects(skills);
        HttpUrl test = call.request().url();
        call.enqueue(new Callback<List<SearchedProject>>() {
            @Override
            public void onResponse(Call<List<SearchedProject>> call, Response<List<SearchedProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        if(response.body()!=null) {
                            List<SearchedProject> filteredList = new ArrayList<SearchedProject>();
                            for (SearchedProject proj : response.body()) {
                                int projPercentage = (int)((proj.getMatches()/(float)proj.getNumOfNeededSkills())*100);
                                if (projPercentage >= 10)
                                    filteredList.add(proj);
                            }

                            mListListener.onProjectListCome(filteredList);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SearchedProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    /**Psotavi listener */
    @Override
    public void setSearchedProjectListListener(SearchedProjectListListener listListener) {
        mListListener = listListener;
    }
}
