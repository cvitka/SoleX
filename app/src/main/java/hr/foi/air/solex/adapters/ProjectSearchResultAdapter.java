package hr.foi.air.solex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hr.foi.air.solex.R;
import hr.foi.air.solex.models.searched_project.SearchedProject;

public class ProjectSearchResultAdapter extends RecyclerView.Adapter<ProjectSearchResultAdapter.ViewHolder> {


    public interface ClickListener {
        void onItemClick(SearchedProject searchedProject);
    }

    Context mContext;
    private int numberOfSearchedSkills;
    public List<SearchedProject> projectList;
    private final ClickListener mListener;

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

        public void bind(final SearchedProject item, final ProjectSearchResultAdapter.ClickListener listener, int numberOfSearchedSkills) {
            companyName.setText(item.getComapanyName());
            projectName.setText(item.getProjectName());
            collaborationName.setText(item.getCollaborationName());
            int sum = (item.getApplicantsNum());
            matches.setText(String .valueOf(sum)+" applications");
            int percent = (int)((item.getMatches()/(float)(numberOfSearchedSkills))*100);
            percentage.setText(String.valueOf(percent)+"% match");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searched_projects_row, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(projectList.get(position), mListener, numberOfSearchedSkills);
    }

    public ProjectSearchResultAdapter( List<SearchedProject> projectList, ClickListener mListener, int numberOfSearchedSkills) {
        this.mListener = mListener;
        this.projectList = projectList;
        this.numberOfSearchedSkills = numberOfSearchedSkills;
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
