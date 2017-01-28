package hr.foi.air.solex.models.profile_screen_project;

import android.util.Log;

import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.WebServiceCommunicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ProfileScreenProjectInteractorImpl extends WebServiceCommunicator implements ProfileScreenProjectInteractor {
    private ProfileScreenProjectListListener mListListener;
    private AddHighlightListener mAddListener;
    private RemoveHighlightListener mRemoveListener;

    private interface WSInterfaceProject {
        @GET("dohvatiProjekte.php")
        Call<List<ProfileScreenProject>> getProjects(@Query("tipDohvacanja") String tipDohvacanja, @Query("id") int id);

        @GET("dodajIstaknutuSuradnju.php")
        Call<WSResponseProfileScreenProject> addHighlightedProjectCompany(@Query("companyID") int companyID, @Query("projektID") int projectID);

        @GET("dodajIstaknutuSuradnju.php")
        Call<WSResponseProfileScreenProject> addHighlightedProjectDeveloper(@Query("developerID") int developerID, @Query("projektID") int projectID);

        @GET("removeIstaknutuSuradnju.php")
        Call<WSResponseProfileScreenProject> removeHighlightedProjectCompany(@Query("companyID") int companyID, @Query("projektID") int projectID);

        @GET("removeIstaknutuSuradnju.php")
        Call<WSResponseProfileScreenProject> removeHighlightedProjectDeveloper(@Query("developerID") int developerID, @Query("projektID") int projectID);
    }


    public ProfileScreenProjectInteractorImpl(ProfileScreenProjectListListener listener) {
        initRetrofit();
        this.mListListener = listener;
    }

    @Override
    public void getHighlightedProjectList(int id, UserType userType) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        String tipDohvacanja = "";
        if (userType.intVal() == UserType.COMPANY.intVal())
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

    @Override
    public void getAllProjectList(int id, UserType userType) {
        String action;
        if (userType == UserType.COMPANY)
            action = "AllProjectListCompany";
        else
            action = "AllProjectListDeveloper";
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<List<ProfileScreenProject>> call = interfaceProject.getProjects(action, id);
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
    public void addToHighlighted(int projectID, int userId, UserType userType) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<WSResponseProfileScreenProject> call;
        if(userType==UserType.COMPANY)
            call = interfaceProject.addHighlightedProjectCompany(userId, projectID);
        else
            call = interfaceProject.addHighlightedProjectDeveloper(userId, projectID);
        call.enqueue(new Callback<WSResponseProfileScreenProject>() {
            @Override
            public void onResponse(Call<WSResponseProfileScreenProject> call, Response<WSResponseProfileScreenProject> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1")) {
                        if (mAddListener != null) {
                            mAddListener.onHighlightsAdd();
                        }

                    } else {
                        if (mAddListener != null) {
                            mAddListener.onHighlightsAddFailure(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WSResponseProfileScreenProject> call, Throwable t) {
                mAddListener.onHighlightsAddFailure(t.getMessage());
            }
        });
    }

    @Override
    public void setAddHighlightListener(AddHighlightListener listener) {
        mAddListener = listener;
    }

    @Override
    public void removeHighlighted(int projectID, int userId, UserType userType) {
       WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<WSResponseProfileScreenProject> call;
        if(userType==UserType.COMPANY)
            call = interfaceProject.removeHighlightedProjectCompany(userId, projectID);
        else
            call = interfaceProject.removeHighlightedProjectDeveloper(userId, projectID);
        call.enqueue(new Callback<WSResponseProfileScreenProject>() {
            @Override
            public void onResponse(Call<WSResponseProfileScreenProject> call, Response<WSResponseProfileScreenProject> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1")) {
                        if (mRemoveListener != null) {
                            mRemoveListener.onRemove();
                        }

                    } else {
                        if (mRemoveListener != null) {
                            mRemoveListener.onRemoveFailure(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WSResponseProfileScreenProject> call, Throwable t) {

            }
        });
    }

    @Override
    public void setRemoveHighlightListener(RemoveHighlightListener listener) {
        mRemoveListener = listener;

    }

    @Override
    public void getProjectList() {

    }
}
