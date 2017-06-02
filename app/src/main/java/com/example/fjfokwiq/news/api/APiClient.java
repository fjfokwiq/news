package com.example.fjfokwiq.news.api;

import com.example.fjfokwiq.news.MyApplication;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class APiClient {
    public static final String BASE_URL = "http://118.244.212.82:9092/newsClient/";
    public NewsApi newsService;

    public NewsApi getNewsService() {
        return newsService;
    }

    APiClient() {
        File cacheDirectory=new File(MyApplication.context.getCacheDir(),"cache");
        if (!cacheDirectory.exists()){
            try {
                cacheDirectory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(cacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CacheInte())
                .cache(cache)
                .build();

        Retrofit news=new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        newsService = news.create(NewsApi.class);
    }

}
