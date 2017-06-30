package com.example.fjfokwiq.news.api;

import com.example.fjfokwiq.news.MyApplication;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class APiClient {
    public static final String BASE_URL = "http://118.244.212.82:9092/newsClient/";
    public static final String LOGIN_URL = "http://192.168.116.2:8080/LoginServer/";
    public NewsApi newsService;
    public LoginAPi loginService;
    public NewsApi getNewsService() {
        return newsService;
    }
    public LoginAPi getLoginService() {
        return loginService;
    }

    APiClient() {
        File httpCacheDirectory = new File(MyApplication.context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor())
                .cache(cache).build();


        Retrofit news = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Retrofit login = new Retrofit
                .Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        newsService = news.create(NewsApi.class);

        loginService = login.create(LoginAPi.class);
    }

}
