package com.example.fjfokwiq.news.utlis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by fjfokwiq on 2017/5/7.
 */

public class IntentStateUtli {
    private static ConnectivityManager manager;


    public static boolean isNetworkAvailable(Context context) {
        if(context !=null){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if(info !=null){
                return info.isConnected();
            }
        }
        return false;
    }
}
