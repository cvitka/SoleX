package hr.foi.air.solex.models.projects;

import hr.foi.air.solex.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SelectedProjectInteractorImpl extends WebServiceCommunicator implements SelectedProjectInteractor {

    private interface WSInterfaceSelectedProject{
        @GET("dohvatiOdabraniProjekt.php")
        Call<WSResponseSelectedProject> dohvatiProjekt(@Query("id") int id);
    }

    SelectedProjectListener mScalarListener;


    public SelectedProjectInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void getSelectedProjectData(int id) {

        WSInterfaceSelectedProject interfaceProject = retrofit.create(WSInterfaceSelectedProject.class);
        Call<WSResponseSelectedProject> call = interfaceProject.dohvatiProjekt(id);
        if (call!=null){
            call.enqueue(new Callback<WSResponseSelectedProject>() {
                @Override
                public void onResponse(Call<WSResponseSelectedProject> call, Response<WSResponseSelectedProject> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if (mScalarListener!=null){
                                Project project = new Project();
                                project.setId(response.body().getId());
                                project.setName(response.body().getNaziv());
                                project.setDescription(response.body().getOpisProjekta());
                                mScalarListener.onDataCome(project);

                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<WSResponseSelectedProject> call, Throwable t) {

                }
            });
        }

    }

    @Override
    public void setScalarListener(SelectedProjectListener listener) {
        this.mScalarListener = listener;

    }
}
