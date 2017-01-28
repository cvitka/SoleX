package hr.foi.air.solex.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.foi.air.solex.R;
import hr.foi.air.solex.models.applicants.Applicant;
import hr.foi.air.solex.models.collab_applicat.CollabApplicat;

public class ApplicantsAdapter  extends ArrayAdapter<Applicant> {

    private List<Applicant> items;
    private int numOfSkills;
    private Context ctx;
    private int itemResId;

    public ApplicantsAdapter(Context context, int textViewResourceId, List<Applicant> items, int numOfSkills) {
        super(context, textViewResourceId, items);
        this.items = items;
        this.ctx = context;
        this.itemResId = textViewResourceId;
        this.numOfSkills = numOfSkills;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView; //v=holder
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(itemResId, null);
        }
        Applicant o = items.get(position);
        if (o != null) {
            TextView lblApplicantName = (TextView) v.findViewById(R.id.applicant_tvDeveloperName);
            TextView lblPercentage = (TextView) v.findViewById(R.id.applicant_tvPercentage);
            TextView lblDate = (TextView) v.findViewById(R.id.applicant_tvApplyDate);

            lblApplicantName.setText(o.getApplicantName() + " "+o.getApplicantSurname());
            //postotak se izračunava
            //trebalo bi implementirati da se ograniče
            int percentage = (int)((o.getMatches()/(float)(this.numOfSkills))*100);
            lblPercentage.setText(String.valueOf(percentage));
            lblDate.setText(o.getApplicationDate());
        }
        return v;
    }
}
