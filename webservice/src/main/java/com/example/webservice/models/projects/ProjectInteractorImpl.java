package com.example.webservice.models.projects;

import com.example.webservice.models.WebServiceCommunicator;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProjectInteractorImpl extends WebServiceCommunicator implements ProjectInteractor {

    CreateProjectListener mCreateProjectListener;

    private interface WSInterfaceAddProject {

        @GET("dodajProjekt.php")
        Call<WSResponseProject> addProject(@Query("name") String name, @Query("description") String description, @Query("date") String startDate, @Query("state") int stateId, @Query("companyID") int companyId);
    }

    public ProjectInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void createNewProject(Project project) {
        WSInterfaceAddProject interfaceAddProject = retrofit.create(WSInterfaceAddProject.class);
        Call<WSResponseProject> call = interfaceAddProject.addProject(project.getName(), project.getDescription(), project.getStartDate(), project.getStateId(), project.getCompanyId());
        if (call != null) {
            call.enqueue(new Callback<WSResponseProject>() {
                @Override
                public void onResponse(Call<WSResponseProject> call, Response<WSResponseProject> response) {
                    if(response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if(mCreateProjectListener != null){
                                mCreateProjectListener.onProjectCreate();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<WSResponseProject> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void setCreateListener(CreateProjectListener listener) {
        mCreateProjectListener = listener;

    }


}
