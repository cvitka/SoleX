package hr.foi.air.solex.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import hr.foi.air.solex.R;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;
import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;

public class CollabApplicatAdapter extends ArrayAdapter<CollabApplicat>{

    public interface ClickListener {/** interface za promjenu ocjene, kako bi znali obojati zvjezdice */
        void onRatingChanged(CollabApplicat collab, int rating);
    }

    private List<CollabApplicat> items;
    private Context ctx;
    private int itemResId;
    private char mCollabApplicat;
    private ClickListener listener;

    public CollabApplicatAdapter(Context context, int resId, List<CollabApplicat> items, char collabApplicat) {
        super(context, resId, items);
        this.items = items;
        this.ctx = context;
        this.itemResId = resId;
        this.mCollabApplicat = collabApplicat;
    }

    public CollabApplicatAdapter(Context context, int resId, List<CollabApplicat> items, char collabApplicat, ClickListener listener) {
        super(context, resId, items);
        this.items = items;
        this.ctx = context;
        this.itemResId = resId;
        this.mCollabApplicat = collabApplicat;
        this.listener = listener;
    }


    private void setListenerOnRatingBar(final CollabApplicat o, RatingBar rb){/** listener za gore navedeni interface */
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                listener.onRatingChanged(o, (int)rating);
            }
        });
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView; //v=holder
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(itemResId, null);
        }
        CollabApplicat o = items.get(position);
        if (o != null) {
            TextView lblCollabName = (TextView) v.findViewById(R.id.collab_applicat_tvCollaborationName);
            TextView lblProjectName = (TextView) v.findViewById(R.id.collab_applicat_tvProjectName);
            TextView lblCompanyName = (TextView) v.findViewById(R.id.collab_applicat_tvCompanyName);

            TextView lblDateLabel = (TextView) v.findViewById(R.id.collab_applicat_tvDateLabel);
            TextView lblDate = (TextView) v.findViewById(R.id.collab_applicat_tvDate);

            TextView lblCollabModel = (TextView) v.findViewById(R.id.collab_applicat_tvCollabModel);
            TextView lblPayment = (TextView) v.findViewById(R.id.collab_applicat_tvPayment);

            lblProjectName.setText(o.getProjectName());
            lblCompanyName.setText(o.getCompanyName());
            lblCollabName.setText(o.getCollaborationName());
            lblCollabModel.setText(String.valueOf((char)(o.getCollaborationType()+'A'-1)));
            lblPayment.setText(String.valueOf(o.getPay()));

            if(mCollabApplicat == 'c'){
                lblDateLabel.setText(ctx.getResources().getString(R.string.tvDateStartedLabel));
                lblDate.setText(o.getCollaborationStartedDate());
                RatingBar ratingBar = (RatingBar)v.findViewById(R.id.collab_applicat_ratingBar);
                ratingBar.setRating((int)o.getRating());
                setListenerOnRatingBar(o, ratingBar);
            }
            else if(mCollabApplicat == 'a'){
                lblDateLabel.setText(ctx.getResources().getString(R.string.tvDateApplicatedLabel));
                lblDate.setText(o.getApplicationDate());
                if(o.getApplicationState().equals("p")){
                    v.setBackgroundColor(ctx.getResources().getColor(R.color.light_gray_list_background));
                }
                else
                    v.setBackgroundColor(ctx.getResources().getColor(R.color.white));
            }

        }
        return v;
    }

}
