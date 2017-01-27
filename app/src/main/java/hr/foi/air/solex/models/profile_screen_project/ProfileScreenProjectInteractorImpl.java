package hr.foi.air.solex.models.profile_screen_project;

import android.util.Log;

import hr.foi.air.solex.models.login_registration.User;
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
    AddHighlightListener mAddListener;
    UpdateHighlightListener mUpdateListener;

    private interface WSInterfaceProject {
        @GET("dohvatiProjekte.php")
        Call<List<ProfileScreenProject>> getProjects(@Query("tipDohvacanja") String tipDohvacanja, @Query("id") int id);

        @GET("dodajIstaknutuSuradnju.php")
        Call<WSResponseProfileScreenProject> addHighlightedProject(@Query("companyID") int companyID, @Query("projektID") int projectID, @Query("status") int status);

        @GET("updateIstaknuteSuradnje.php")
        Call<WSResponseProfileScreenProject> updateHighlightedProject(@Query("companyID") int companyID, @Query("projektID") int projectID);
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
    public void addToHighlighted(int projectID) {
        WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<WSResponseProfileScreenProject> call = interfaceProject.addHighlightedProject(User.getInstance().getId(), projectID, '1');
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

            }
        });
    }

    @Override
    public void setAddHighlightListener(AddHighlightListener listener) {
        mAddListener = listener;
    }

    @Override
    public void updateToHighlighted(int projectID) {
       WSInterfaceProject interfaceProject = retrofit.create(WSInterfaceProject.class);
        Call<WSResponseProfileScreenProject> call = interfaceProject.updateHighlightedProject(User.getInstance().getId(), projectID);
        call.enqueue(new Callback<WSResponseProfileScreenProject>() {
            @Override
            public void onResponse(Call<WSResponseProfileScreenProject> call, Response<WSResponseProfileScreenProject> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1")) {
                        if (mUpdateListener != null) {
                            mUpdateListener.onUpdate();
                        }

                    } else {
                        if (mUpdateListener != null) {
                            mUpdateListener.onUpdateFailure(response.body().getMessage());
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
    public void setUpdateHighlightListener(UpdateHighlightListener listener) {
        mUpdateListener = listener;

    }

    @Override
    public void getProjectList() {

    }
}
