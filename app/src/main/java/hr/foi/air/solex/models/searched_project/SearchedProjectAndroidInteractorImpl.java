package hr.foi.air.solex.models.searched_project;


import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import hr.foi.air.solex.models.WebServiceCommunicator;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SearchedProjectAndroidInteractorImpl extends WebServiceCommunicator implements SearchedProjectInteractor {
    private SearchedProjectListListener mListListener;

    public SearchedProjectAndroidInteractorImpl() {
        initRetrofit();
    }

    private interface WSInterfaceProject {
        @GET("Module2ApiProjects.php")
        Call<List<SearchedProject>> getProjects(@Query("skills[]") List<String> skills);

        @GET("Module2Lucky.php")
        Call<List<SearchedProject>> getLucky(@Query("skills[]") List<String> skills);
    }

    @Override
    public void searchProjects(final int percentage, final List<String> skills) {
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
                                int projPercentage = (int)((proj.getMatches()/(float)skills.size())*100);
                                if (projPercentage >= percentage)
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

    @Override
    public void luckySearchProjects(List<String> skills) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<SearchedProject>> call = interfaceProject.getLucky(skills);
        HttpUrl test = call.request().url();
        call.enqueue(new Callback<List<SearchedProject>>() {
            @Override
            public void onResponse(Call<List<SearchedProject>> call, Response<List<SearchedProject>> response) {
                if (response.isSuccessful()) {
                    if (mListListener != null) {
                        List<SearchedProject>projects = response.body();
                        List<SearchedProject> luckyProjects;
                        if (projects.size() > 3) {
                            int a, b, c;
                            Random rand = new Random();
                            a = rand.nextInt(projects.size());
                            do{
                                b = rand.nextInt(projects.size());
                            }while(a==b);

                            do{
                                c = rand.nextInt(projects.size());
                            }while(c==a || c==b);
                            luckyProjects = new ArrayList<>();
                            luckyProjects.add(projects.get(a));
                            luckyProjects.add(projects.get(b));
                            luckyProjects.add(projects.get(c));
                        }
                        else luckyProjects = response.body();

                        mListListener.onProjectListCome(luckyProjects);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SearchedProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void setSearchedProjectListListener(SearchedProjectListListener listListener) {
        mListListener = listListener;
    }
}
