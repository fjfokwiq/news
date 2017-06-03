package com.example.fjfokwiq.news.api;

import com.example.fjfokwiq.news.bean.NewsRequest;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;


public interface NewsApi {
   /* url="http://118.244.212.82:9092/newsClient/news_list" +
            "?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";*/

     @Headers("Cache-Control:public,max-age=3600")
     @GET("news_list")
    Observable<NewsRequest> getNewsJson(@QueryMap Map<String,String>params);

}
