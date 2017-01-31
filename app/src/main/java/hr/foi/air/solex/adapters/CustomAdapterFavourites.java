package hr.foi.air.solex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import hr.foi.air.solex.models.favorites.ApiFavourites;

import java.util.ArrayList;

import hr.foi.air.solex.R;

class CustomAdapterFavourites extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<ApiFavourites> mDataSource;

    public CustomAdapterFavourites(Context context, ArrayList<ApiFavourites> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            /**Inflate the custom row layout from your XML.*/
            convertView = mInflater.inflate(R.layout.custom_row_favourites, parent, false);

            /** create a new "Holder" with subviews*/
            holder = new ViewHolder();
            //holder.name = (TextView) convertView.findViewById(R.id.crName);
           // holder.surname = (TextView) convertView.findViewById(R.id.cRsurname);


            /** hang onto this holder for future recyclage*/
            convertView.setTag(holder);
        }
        else {

            // skip all the expensive inflation/findViewById and just get the holder you already made
            holder = (ViewHolder) convertView.getTag();
        }
        TextView nameView = holder.name;
        TextView surnameView = holder.surname;

        ApiFavourites project = (ApiFavourites) getItem(i);

        nameView.setText(project.getDevName());
        surnameView.setText(project.getDevSurname());

        return convertView;

    }


    private static class ViewHolder {
        public TextView name;
        public TextView surname;

    }

    /*public CustomAdapterFavourites(Context context, String[] resource) {
        super(context, R.layout.custom_row_favourites,  resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row_favourites, parent, false);

        String item = getItem(position);
        TextView mName = (TextView) customView.findViewById(R.id.crName);
        //TextView mSurname = (TextView) customView.findViewById(R.id.cRsurname);

        mName.setText(item);
        return customView;
    }*/
}
