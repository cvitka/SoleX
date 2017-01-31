package hr.foi.air.solex.models.projects;

import android.util.Log;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.login_registration.User;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProjectInteractorImpl extends WebServiceCommunicator implements ProjectInteractor {

    CreateProjectListener mCreateProjectListener;


    ProjectListListener mListListener;

    /**Definiranje web service end point-a*/
    private interface WSInterfaceProject {

        @GET("dodajProjekt.php")
        Call<WSResponseProject> addProject(@Query("name") String name, @Query("description") String description, @Query("date") String startDate, @Query("state") int stateId, @Query("companyID") int companyId);

        @GET("dohvatiProjekte.php")
        Call<List<ApiProject>> getProjects(@Query("companyID") int companyID);
    }

    public ProjectInteractorImpl() {
        initRetrofit();
    }

    /**Dodaj novi projekt*/
    @Override
    public void createNewProject(Project project) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<WSResponseProject> call = interfaceProject.addProject(project.getName(), project.getDescription(), project.getStartDate(), project.getStateId(), project.getCompanyId());
        HttpUrl str = call.request().url();
        if (call != null) {
            call.enqueue(new Callback<WSResponseProject>() {
                @Override
                public void onResponse(Call<WSResponseProject> call, Response<WSResponseProject> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            if (mCreateProjectListener != null) {
                                mCreateProjectListener.onProjectCreate(response.body().getNewProjectId());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WSResponseProject> call, Throwable t) {
                    Log.d("Api", t.getMessage());
                }
            });
        }
    }

    /**Postavi listener*/
    @Override
    public void setCreateListener(CreateProjectListener listener) {
        mCreateProjectListener = listener;
    }

    @Override
    public void setListListener(ProjectListListener listListener) {
        mListListener = listListener;
    }

    /**Dohvati listu projekata */
    @Override
    public void getProjectList() {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<ApiProject>> call = interfaceProject.getProjects(User.getInstance().getId());
        call.enqueue(new Callback<List<ApiProject>>() {
            @Override
            public void onResponse(Call<List<ApiProject>> call, Response<List<ApiProject>> response) {
                if (response.body().size()>=1) {
                    if (mListListener != null) {
                        mListListener.onProjectListCome(response.body());

                    }
                }
                else {
                    //prazna lista

                }
            }

            @Override
            public void onFailure(Call<List<ApiProject>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }


}