package hr.foi.air.solex.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Locale;

public class Utility {
    public static void setListViewHeightBasedOnChildren(ListView listView, int maxItems) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        if (listAdapter.getCount() <= maxItems) {
            int totalHeight = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        } else {
            View item = listAdapter.getView(0, null, listView);
            item.measure(0, 0);
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = (int) (5.5 * item.getMeasuredHeight());
            listView.setLayoutParams(params);
        }
    }


    public void setLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String lang = preferences.getString("pref_lang", "en");

        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(new Locale(lang));
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

    }


}
