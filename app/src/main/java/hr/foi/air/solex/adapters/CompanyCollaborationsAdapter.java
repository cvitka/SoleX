package hr.foi.air.solex.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import hr.foi.air.solex.models.collaboration.ApiCompanyCollaborations;
import hr.foi.air.solex.models.favorites.ApiFavourites;

import java.util.List;

import butterknife.BindView;
import hr.foi.air.solex.R;

public class CompanyCollaborationsAdapter extends RecyclerView.Adapter<CompanyCollaborationsAdapter.ViewHolder> {

    public interface ClickListener {
        void onItemClick(ApiCompanyCollaborations companyCollaborations);
        void onItemLongClick(ApiCompanyCollaborations companyCollaborations);
        void onRatingChanged(ApiCompanyCollaborations companyCollaborations, int rating);
    }

    @BindView(R.id.collaboration_list_addToFavorites)
    ImageView addToFavorites;

    private List<ApiCompanyCollaborations> mProjectList;
    private final ClickListener mListener;

    Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView devInfo, projectName;
        public RatingBar ratingBar;
        public ImageView addToFavorites;

        public ViewHolder(final View itemView) {
            super(itemView);
            devInfo = (TextView) itemView.findViewById(R.id.collaboration_list_developerInfo);
            projectName = (TextView) itemView.findViewById(R.id.collaboration_list_ProjectName);
            addToFavorites = (ImageView) itemView.findViewById(R.id.collaboration_list_addToFavorites);
            ratingBar = (RatingBar)itemView.findViewById(R.id.collaboration_list_ratingBar);
        }

        public void bind(final ApiCompanyCollaborations item, final ClickListener listener) {
            devInfo.setText(item.getDevName() + " " + item.getDevSurname());
            projectName.setText(item.getProjectName());
            ratingBar.setRating((int)item.getOcjena());
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    listener.onRatingChanged(item, (int)rating);
                }
            });
            final int color = Color.parseColor("#31C3E7");
            if (item.getFavorit() != null && item.getFavorit() == '1') {
                addToFavorites.setColorFilter(color);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onItemLongClick(item);
                    addToFavorites.setColorFilter(color);
                    return true;
                }
            });
        }
    }

    public CompanyCollaborationsAdapter(List<ApiCompanyCollaborations> projectList, ClickListener listener) {
        mProjectList = projectList;
        mListener = listener;
    }


    @Override
    public CompanyCollaborationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collaboration_list_row, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CompanyCollaborationsAdapter.ViewHolder holder, int position) {
        holder.bind(mProjectList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }

}