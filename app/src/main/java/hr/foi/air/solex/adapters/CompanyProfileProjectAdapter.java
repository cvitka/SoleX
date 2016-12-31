package hr.foi.air.solex.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.webservice.models.profile_screen_project.ProfileScreenProject;

import java.util.List;

import hr.foi.air.solex.R;
import hr.foi.air.solex.helpers.TypeHelper;

public class CompanyProfileProjectAdapter extends ArrayAdapter<ProfileScreenProject> {
    private List<ProfileScreenProject> items;
    private Context ctx;
    int itemResId;

    public CompanyProfileProjectAdapter(Context context, int textViewResourceId, List<ProfileScreenProject> items) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.ctx = context;
        itemResId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView; //v=holder
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_company_profile_highlighted_projects, null);
        }
        ProfileScreenProject o = items.get(position);
        if (o != null) {
            TextView lblCollabNum = (TextView) v.findViewById(R.id.company_profile_hproj_tvCollabNum);
            TextView lblProjectName = (TextView) v.findViewById(R.id.company_profile_hproj_tvProjectName);
            TextView lblProjectState = (TextView) v.findViewById(R.id.company_profile_hproj_tvProjectState);
            lblCollabNum.setText(Integer.toString(o.getNumOfCollaborations()));
            lblProjectName.setText(o.getProjectName());
            lblProjectState.setText(TypeHelper.getProjectState(ctx, o.getState()));
        }
        return v;
    }
}
