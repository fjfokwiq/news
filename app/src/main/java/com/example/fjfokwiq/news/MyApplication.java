package com.example.fjfokwiq.news;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by fjfokwiq on 2017/4/20.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this.getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }
   
        LeakCanary.install(this);

    }


}
