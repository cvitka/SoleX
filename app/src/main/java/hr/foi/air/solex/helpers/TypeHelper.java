package hr.foi.air.solex.helpers;


import android.content.Context;

import hr.foi.air.solex.R;

public class TypeHelper {
    public static String getProjectState(Context ctx, int stateId){
        switch(stateId){
            case 1:
                return ctx.getResources().getString(R.string.project_state_1);
            case 2:
                return ctx.getResources().getString(R.string.project_state_2);
            case 3:
                return ctx.getResources().getString(R.string.project_state_3);
            case 4:
                return ctx.getResources().getString(R.string.project_state_4);
            case 5:
                return ctx.getResources().getString(R.string.project_state_5);
            default:
                return "Invalid type";
        }
    }
}
