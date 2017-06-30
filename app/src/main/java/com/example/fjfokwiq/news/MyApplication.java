package com.example.fjfokwiq.news;

import android.app.Application;
import android.content.Context;

import com.example.fjfokwiq.news.utlis.CommonUtil;
import com.squareup.leakcanary.LeakCanary;

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        CommonUtil.initPreferences();
        loadLeakCanary();


    }

    /*加载内存泄漏分析*/
    private void loadLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {

            return;
        }

        LeakCanary.install(this);
    }


}
