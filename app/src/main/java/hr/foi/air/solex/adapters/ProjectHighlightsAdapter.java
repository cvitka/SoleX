package hr.foi.air.solex.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import hr.foi.air.solex.R;
import hr.foi.air.solex.helpers.TypeHelper;
import hr.foi.air.solex.models.profile_screen_project.ProfileScreenProject;

public class ProjectHighlightsAdapter extends RecyclerView.Adapter<ProjectHighlightsAdapter.ViewHolder> {

    public interface ClickListener {
        void onItemClick(ProfileScreenProject profileScreenProject);
        void onItemLongClick(ProfileScreenProject profileScreenProject);
    }

    @BindView(R.id.projects_list_addToHighlights)
    ImageView addToHighlights;

    private List<ProfileScreenProject> mProjectList;
    private final ClickListener clickListener;
    private Context context;

    @Override
    public ProjectHighlightsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {    /** inflate layout */

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_projects_highligths, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProjectHighlightsAdapter.ViewHolder holder, int position) {
        holder.bind(mProjectList.get(position), clickListener);    /** vezanje liste projekata sa listenerom*/

    }


    @Override
    public int getItemCount() {
        return mProjectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName, projectState, numOfCollab;
        public ImageView addToHighlights;

        public ViewHolder(View itemView) {    /** pronalazenje itema iz layouta */
            super(itemView);
            projectName = (TextView) itemView.findViewById(R.id.company_proj_tvProjectName);
            projectState = (TextView) itemView.findViewById(R.id.company_proj_tvProjectState);
            numOfCollab = (TextView) itemView.findViewById(R.id.company_proj_tvCollabNum);
            addToHighlights = (ImageView) itemView.findViewById(R.id.projects_list_addToHighlights);
        }

        public void bind(final ProfileScreenProject item, final ClickListener clickListener){
            final int color = Color.parseColor("#31C3E7");
            numOfCollab.setText(Integer.toString(item.getNumOfCollaborations())+" "+context.getString(R.string.collaborations));
            projectState.setText(TypeHelper.getProjectState(context, item.getState()));
            projectName.setText(item.getProjectName());
            if (item.getHighlightedStatus() != 0) {    /** dodavanje zvjezdica pri ucitavanju */
                addToHighlights.setColorFilter(color);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(item);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onItemLongClick(item);
                    if(item.getHighlightedStatus()==0)
                        addToHighlights.setColorFilter(context.getResources().getColor(android.R.color.white));
                    else
                        addToHighlights.setColorFilter(color);  /** bojanje zvjezdice */
                    return true;
                }
            });
        }
    }

    public ProjectHighlightsAdapter(List<ProfileScreenProject> mProjectList, ClickListener clickListener) {
        this.mProjectList = mProjectList;
        this.clickListener = clickListener;
    }
}
