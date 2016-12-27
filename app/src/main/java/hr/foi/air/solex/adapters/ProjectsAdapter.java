package hr.foi.air.solex.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.webservice.models.projects.ApiProject;

import java.util.List;;
import hr.foi.air.solex.R;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {



    private List<ApiProject> mProjectList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.project_list_Name);
        }
    }

    public ProjectsAdapter(List<ApiProject> projectList) {
        mProjectList = projectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.project_list_row, parent, false);
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApiProject project = mProjectList.get(position);
        holder.name.setText(project.getNaziv());
    }


    @Override
    public int getItemCount() {
        return mProjectList.size();
    }


}
