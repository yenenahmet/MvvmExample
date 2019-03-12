package com.example.mvvmexample.util;

import android.app.Activity;
import android.content.Intent;

public class Utility {
    public static final String appId = "9ecf7b693a6624e15824f548885247c1";

    public static void openIntent(final Activity activity,final Class clas){
        if(activity!=null && clas!=null){
            final Intent nt = new Intent(activity,clas);
            activity.startActivity(nt);
        }
    }

    public static String stringNullControl(final String val){
        if(val==null){
            return "";
        }
        return val;
    }

    public static int intNullControl(final Integer val){
        if(val==null){
            return 0;
        }
        return val;
    }
}
