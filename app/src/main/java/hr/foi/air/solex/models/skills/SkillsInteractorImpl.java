package hr.foi.air.solex.models.skills;

import android.util.Log;

import hr.foi.air.solex.utils.UserType;
import hr.foi.air.solex.models.WebServiceCommunicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SkillsInteractorImpl extends WebServiceCommunicator implements SkillsInteractor {

    /**Definiranje web service end point-a */
    private interface WSInterface {
        @GET("strucnosti.php")
        Call<List<String>> dohvatiStrucnosti(@Query("akcija") String action, @Query("id") int id);
        @GET("strucnosti.php")
        Call<List<String>> dohvatiSveStrucnosti(@Query("akcija") String action);
        @GET("strucnosti.php")
        Call<WSResponseSkill> obrisiStrucnost(@Query("akcija") String action, @Query("strucnost") String skill, @Query("id") int id);
        @GET("strucnosti.php")
        Call<WSResponseSkill> dodajStrucnost(@Query("akcija") String action, @Query("strucnost") String skill, @Query("id") int id);
    }

    private SkillListListener skillListListener;
    private SkillDeleteListener deleteListener;
    private SkillUpdateListener updateListener;
    private AllSkillListListener allSkillListListener;

    public SkillsInteractorImpl(){
        initRetrofit();
    }

    /**Postavljanje listenera */

    public void setSkillListListener(SkillListListener listener){
        this.skillListListener = listener;
    }

    public void setAllSkillListListener(AllSkillListListener listener){
        this.allSkillListListener = listener;
    }

    public void setUpdateListener(SkillUpdateListener listener){
        this.updateListener = listener;
    }

    public void setDeleteListener(SkillDeleteListener listener){
        this.deleteListener = listener;
    }

    /**Dohvati listu kompetencija za odredenu suradnju */
    @Override
    public void getCollaborationSkillList(int collaborationId) {
        WSInterface wsInterface = retrofit.create(WSInterface.class);
        Call<List<String>> call = wsInterface.dohvatiStrucnosti("dohvatiStrucnostiSuradnje", collaborationId);
        if (call!=null)
        {
            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if(response.isSuccessful())
                    {
                        if (skillListListener != null)
                        {
                            skillListListener.onSkillListCome(response.body());
                        }
                    }
                    else
                        Log.w("no listener", "listener = null");
                }
                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Log.w("onFailure", t.getMessage());
                }
            });
        }
    }

    /**Dohvati sve kompetencije */
    @Override
    public void getAllSkillList(){
        WSInterface wsInterface = retrofit.create(WSInterface.class);
        Call<List<String>> call = wsInterface.dohvatiSveStrucnosti("dohvatiSveStrucnosti");
        if (call!=null)
        {
            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if(response.isSuccessful())
                    {
                        if (allSkillListListener != null)
                        {
                            allSkillListListener.onAllSkillListCome(response.body());
                        }
                    }
                    else
                        Log.w("no listener", "listener = null");
                }
                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Log.w("onFailure", t.getMessage());
                }
            });
        }
    }

    /**Dohvati listu kompetencija za korisnika */
    @Override
    public void getSkillList(int id, UserType user) {
        WSInterface wsInterface = retrofit.create(WSInterface.class);
        //Call<List<ApiNeededCollaborations>> call =
        Call<List<String>> call;
        String akcija;
        if(user.intVal()==UserType.COMPANY.intVal())
            akcija="dohvatiStrucnostiPoduzeca";
        else //if(user.intVal()==UserType.DEVELOPER.intVal())
            akcija="dohvatiStrucnostiDevelopera";

        call = wsInterface.dohvatiStrucnosti(akcija, id);
        if (call!=null)
        {
            call.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    if(response.isSuccessful())
                    {
                        if (skillListListener != null)
                        {
                            skillListListener.onSkillListCome(response.body());
                        }
                    }
                    else
                        Log.w("no listener", "listener = null");
                }
                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Log.w("onFailure", t.getMessage());
                }
            });
        }
    }


    /**Ukloni kompetenciju za korisnika */
    @Override
    public void deleteSkill(int id, String skill, UserType user) {
        WSInterface servInterface = retrofit.create(WSInterface.class);
        Call<WSResponseSkill> call;
        String akcija;
        if (user.intVal() == UserType.COMPANY.intVal())
            akcija = "makniStrucnostPoduzecu";
        else //if(user.intVal()==UserType.DEVELOPER.intVal())
            akcija = "makniStrucnostDeveloperu";
        call = servInterface.obrisiStrucnost(akcija, skill, id);
        if (call != null) {
            call.enqueue(new Callback<WSResponseSkill>() {
                @Override
                public void onResponse(Call<WSResponseSkill> call, Response<WSResponseSkill> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            if (deleteListener != null) {
                                deleteListener.deleteSuccessful();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<WSResponseSkill> call, Throwable t) {

                }
            });
        }
    }

    /**Dodaj kompetenciju korisniku */
    @Override
    public void addSkill(int id, String skill, UserType user) {
        WSInterface servInterface = retrofit.create(WSInterface.class);
        Call<WSResponseSkill> call;
        String akcija;
        if (user.intVal() == UserType.COMPANY.intVal())
            akcija = "dodajStrucnostPoduzecu";
        else //if(user.intVal()==UserType.DEVELOPER.intVal())
            akcija = "dodajStrucnostDeveloperu";
        call = servInterface.dodajStrucnost(akcija, skill, id);
        if (call != null) {
            call.enqueue(new Callback<WSResponseSkill>() {
                @Override
                public void onResponse(Call<WSResponseSkill> call, Response<WSResponseSkill> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess().equals("1")) {
                            if (updateListener != null) {
                                updateListener.updateSuccessful();
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<WSResponseSkill> call, Throwable t) {
                    updateListener.updateFailed("Error while updating");
                }
            });
        }
    }
}
