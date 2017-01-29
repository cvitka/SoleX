package hr.foi.air.solex.models.collaboration;

import android.util.Log;

import hr.foi.air.solex.models.WebServiceCommunicator;

import java.util.List;

import hr.foi.air.solex.presenters.companies.ApplicationAcceptedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiNeededCollaborationsInteractorImpl extends WebServiceCommunicator implements ApiNeededCollaborationsInteractor {

    private ApiNeededCollaborationListListener mListener;
    private NeededCollaborationDataScalarListener mNeededCollabDataScalarListener;
    private ApplicationAcceptedListener mApplicationAcceptedListener;
    private DeveloperAppliesListener mDeveloperAppliesListener;
    private PushNotificationListenerDeveloper mPushNotificationListener;
    PushNotificationListenerCompany mPushNotificationListenerCompany;

    private interface WSInterfaceCollaborations {
        @GET("dohvatiPotrebneSuradnje.php")
        Call<List<ApiNeededCollaborations>> getNeededCollaborations(@Query("projektId") int projektId);

        @GET("dohvatiSuradnje.php")
        Call<NeededCollaborationData> getNeededCollab(@Query("neededCollabId") int collaborationId);

        @GET("dohvatiSuradnje.php")
        Call<NeededCollaborationData> getNeededCollab(@Query("collaborationId") int collaborationId, @Query("developerId") int developerId);

        @GET("dodijeliPosao.php")
        Call<NeededCollaborationData> saveApplicationAccepted(@Query("collaborationId") int collaborationId, @Query("developerId") int developerId);

        @GET("apliciraj.php")
        Call<NeededCollaborationData> apply(@Query("akcija") String action, @Query("collaborationId") int collaborationId, @Query("developerId") int developerId);

        @GET("push_notification.php")
        Call<WSResponsePushNotification> sendPushToCompany(@Query("companyId") int companyID);

        @GET("push_notification.php")
        Call<WSResponsePushNotification> sendPushToDeveloper(@Query("developerId") int developerID);

    }

    public ApiNeededCollaborationsInteractorImpl() {
        initRetrofit();
    }

    @Override
    public void getData(int id) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<List<ApiNeededCollaborations>> call = interfaceCollaborations.getNeededCollaborations(id);
        call.enqueue(new Callback<List<ApiNeededCollaborations>>() {
            @Override
            public void onResponse(Call<List<ApiNeededCollaborations>> call, Response<List<ApiNeededCollaborations>> response) {
                if (response.isSuccessful()) {
                    if (mListener != null) {
                        mListener.onDataListCome(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiNeededCollaborations>> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }

    @Override
    public void getNeededCollaboration(int collaborationId) {

        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.getNeededCollab(collaborationId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mNeededCollabDataScalarListener != null) {
                        mNeededCollabDataScalarListener.neededCollaborationDataArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });

    }

    @Override
    public void getNeededCollaboration(int collaborationId, int developerId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.getNeededCollab(collaborationId, developerId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mNeededCollabDataScalarListener != null) {
                        mNeededCollabDataScalarListener.neededCollaborationDataArrived(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void applicationAccepted(int collaborationId, int developerId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.saveApplicationAccepted(collaborationId, developerId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mApplicationAcceptedListener != null) {
                        mApplicationAcceptedListener.onSuccessfulAssign();
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void developerApplied(int collaborationId, int developerId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.apply("add", collaborationId, developerId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mDeveloperAppliesListener != null) {
                        mDeveloperAppliesListener.onApplySuccessfull();
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void developerRemovedApply(int collaborationId, int developerId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<NeededCollaborationData> call = interfaceCollaborations.apply("remove", collaborationId, developerId);
        call.enqueue(new Callback<NeededCollaborationData>() {
            @Override
            public void onResponse(Call<NeededCollaborationData> call, Response<NeededCollaborationData> response) {
                if (response.isSuccessful()) {
                    if (mDeveloperAppliesListener != null) {
                        mDeveloperAppliesListener.onApplyRemoveSuccessfull();
                    }
                }
            }

            @Override
            public void onFailure(Call<NeededCollaborationData> call, Throwable t) {
                Log.d("Api", t.getMessage());
            }
        });
    }

    @Override
    public void pushNotification(int companyId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<WSResponsePushNotification> call = interfaceCollaborations.sendPushToCompany(companyId);
        call.enqueue(new Callback<WSResponsePushNotification>() {
            @Override
            public void onResponse(Call<WSResponsePushNotification> call, Response<WSResponsePushNotification> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1") && response.body().getFailure().equals("0")) {
                        if (mPushNotificationListener != null) {
                            mPushNotificationListener.notificationPushedCompany();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WSResponsePushNotification> call, Throwable t) {

            }
        });
    }

    @Override
    public void setPushNotificationListener(PushNotificationListenerDeveloper listener) {
        mPushNotificationListener = listener;
    }

    @Override
    public void pushNotificationDeveloper(int developerId) {
        WSInterfaceCollaborations interfaceCollaborations = retrofit.create(WSInterfaceCollaborations.class);
        Call<WSResponsePushNotification> call = interfaceCollaborations.sendPushToDeveloper(developerId);
        call.enqueue(new Callback<WSResponsePushNotification>() {
            @Override
            public void onResponse(Call<WSResponsePushNotification> call, Response<WSResponsePushNotification> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("1") && response.body().getFailure().equals("0")) {
                        if (mPushNotificationListenerCompany != null) {
                            mPushNotificationListenerCompany.notificationPushedDeveloper();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<WSResponsePushNotification> call, Throwable t) {

            }
        });
    }

    @Override
    public void setPushNotificationListenerCompany(PushNotificationListenerCompany listenerCompany) {
        mPushNotificationListenerCompany = listenerCompany;
    }

    @Override
    public void setApplicationAcceptedListener(ApplicationAcceptedListener listener) {
        mApplicationAcceptedListener = listener;
    }

    @Override
    public void setNeededCollabDataListener(NeededCollaborationDataScalarListener listener) {
        mNeededCollabDataScalarListener = listener;
    }

    @Override
    public void setListListener(ApiNeededCollaborationListListener neededCollaborationListListener) {
        mListener = neededCollaborationListListener;
    }

    @Override
    public void setDeveloperAppliesListener(DeveloperAppliesListener listener) {
        mDeveloperAppliesListener = listener;
    }
}
