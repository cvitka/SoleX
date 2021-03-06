package hr.foi.air.solex.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import hr.foi.air.solex.R;
import hr.foi.air.solex.models.modularity.SearchedProject;

public class ProjectsFeelingLuckyAdapter extends RecyclerView.Adapter<ProjectsFeelingLuckyAdapter.ViewHolder> {

    Context mContext;
    public List<SearchedProject> mProjectList;
    private final ClickListener mListener;

    public ProjectsFeelingLuckyAdapter(List<SearchedProject> projectList,ClickListener listener) {
        mListener = listener;
        mProjectList = projectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  /** inflate layout */
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searched_projects_row, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mProjectList.get(position), mListener);  /** povezivanje lista i clicklistenera */
    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }

    public interface ClickListener {  /** interface za onClick metodu */
        void onItemClick(SearchedProject searchedProject);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView projectName, collaborationName, companyName, matches, percentage;

        public ViewHolder(final View itemView) {
            super(itemView);
            collaborationName = (TextView) itemView.findViewById(R.id.activity_search_projects_collab_name);
            projectName = (TextView) itemView.findViewById(R.id.activity_search_projects_project_name);
            companyName = (TextView) itemView.findViewById(R.id.activity_search_projects_company_name);
            matches = (TextView) itemView.findViewById(R.id.activity_search_projects_matches);
            percentage = (TextView) itemView.findViewById(R.id.activity_search_projects_project_percentage);
        }

        public void bind(final SearchedProject item, final ProjectsFeelingLuckyAdapter.ClickListener listener) {  /** povezivanje liste i clicklistenera*/
            companyName.setText(item.getComapanyName());
            projectName.setText(item.getProjectName());
            collaborationName.setText(item.getCollaborationName());
            int sum = (item.getApplicantsNum());
            matches.setText(String .valueOf(sum)+mContext.getString(R.string.adapter_applications));
            int percent = (int)((item.getMatches()/(float)(item.getNumOfNeededSkills()))*100);
            percentage.setText(String.valueOf(percent)+mContext.getString(R.string.match));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
