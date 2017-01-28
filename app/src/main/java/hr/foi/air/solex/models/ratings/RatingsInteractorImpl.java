package hr.foi.air.solex.models.ratings;

import android.util.Log;

import java.util.List;

import hr.foi.air.solex.models.WebServiceCommunicator;
import hr.foi.air.solex.models.login_registration.User;
import hr.foi.air.solex.models.skills.AllSkillListListener;
import hr.foi.air.solex.models.skills.SkillDeleteListener;
import hr.foi.air.solex.models.skills.SkillListListener;
import hr.foi.air.solex.models.skills.SkillUpdateListener;
import hr.foi.air.solex.models.skills.WSResponseSkill;
import hr.foi.air.solex.utils.UserType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RatingsInteractorImpl extends WebServiceCommunicator implements RatingsInteractor {
    private interface WSInterface {
        @GET("ocjene.php")
        Call<WSResponseRatings> rateDeveloper(@Query("ocjenaDevelopera") int rating, @Query("companyId") int companyId, @Query("developerId") int developerId, @Query("collaborationId") int collaborationId);
        @GET("ocjene.php")
        Call<WSResponseRatings> rateCompany(@Query("ocjenaPoduzeca") int rating, @Query("companyId") int companyId, @Query("developerId") int developerId, @Query("collaborationId") int collaborationId);
    }

    private RatingsListener mRatingsListener;

    public RatingsInteractorImpl(RatingsListener listener){
        initRetrofit();
        mRatingsListener = listener;
    }

    @Override
    public void rate(int rating, int user, int collaborationId) {
        WSInterface wsInterface = retrofit.create(WSInterface.class);
        Call<WSResponseRatings> call;
        if(User.getInstance().getUserType()==UserType.COMPANY)
            call = wsInterface.rateDeveloper(rating, User.getInstance().getId(), user, collaborationId);
        else{
            call = wsInterface.rateCompany(rating, user,User.getInstance().getId(),  collaborationId);
        }
        if (call!=null)
        {
            call.enqueue(new Callback<WSResponseRatings>() {
                @Override
                public void onResponse(Call<WSResponseRatings> call, Response<WSResponseRatings> response) {
                    if(response.isSuccessful())
                    {
                        if (mRatingsListener != null)
                        {
                            mRatingsListener.onRatingSucceeded();
                        }
                    }
                    else
                        Log.w("no listener", "listener = null");
                }
                @Override
                public void onFailure(Call<WSResponseRatings> call, Throwable t) {
                    Log.w("onFailure", t.getMessage());
                }
            });
        }
    }
}
