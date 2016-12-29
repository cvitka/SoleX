package com.example.webservice.models.collaboration;

import com.example.webservice.models.WebServiceCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NeededCollaborationInteractorImpl extends WebServiceCommunicator implements NeededCollaborationInteractor {

    private interface WSInterfaceCompany {

        @GET("dodajPotrebnuSuradnju.php")
        Call<WSResponseNeededCollaboration> dodajPotrebnuSuradnju(@Query("projektId") int id, @Query("naziv") String naziv, @Query("opis") String opis, @Query("nacinRada") int nacinRada, @Query("naknada") float naknada,@Query("strucnosti") int strucnosti);
    }

    AddNeededCollaborationListener mListener;

    public NeededCollaborationInteractorImpl() {initRetrofit();}

    @Override
    public void addNeededCollaboration(NeededCollaboration neededCollaboration) {
        WSInterfaceCompany servInterface = retrofit.create(WSInterfaceCompany.class);
        Call<WSResponseNeededCollaboration> call = servInterface.dodajPotrebnuSuradnju(neededCollaboration.getProjectId(),neededCollaboration.getName(),neededCollaboration.getDescription(),neededCollaboration.getTypeOfWork(),neededCollaboration.getNaknada(),neededCollaboration.getStrucnosti());
        if (call != null) {
            call.enqueue(new Callback<WSResponseNeededCollaboration>() {
                @Override
                public void onResponse(Call<WSResponseNeededCollaboration> call, Response<WSResponseNeededCollaboration> response) {
                    if (response.isSuccessful()){
                        if(response.body().getSuccess().equals("1")){
                            if(mListener!=null){
                                mListener.onNeededCollaborationAdd();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<WSResponseNeededCollaboration> call, Throwable t) {

                }
            });
        }


    }

    @Override
    public void setAddListener(AddNeededCollaborationListener addNeededCollaborationListener) {
        this.mListener = addNeededCollaborationListener;
    }
}
