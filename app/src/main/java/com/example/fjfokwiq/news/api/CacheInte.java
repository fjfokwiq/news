package com.example.fjfokwiq.news.api;

import com.example.fjfokwiq.news.MyApplication;
import com.example.fjfokwiq.news.utlis.IntentStateUtli;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class CacheInte implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        CacheControl.Builder cacheBuilder=new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365,TimeUnit.DAYS);
        CacheControl control=cacheBuilder.build();
        Request request=chain.request();
        if (!IntentStateUtli.isNetworkAvailable(MyApplication.context)) {
            request = request.newBuilder()
                    .cacheControl(control)
                    .build();
        }
            Response response = chain.proceed(request);
            if (IntentStateUtli.isNetworkAvailable(MyApplication.context)) {
                int maxAge = 60;
                return response.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale=60*60*24*28;
                return response.newBuilder()
                        .removeHeader("pragma")
                        .header("Cache-Control","public, only-if-cached,max-stale="+maxStale)
                        .build();
            }
    }
}