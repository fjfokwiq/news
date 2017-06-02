package com.example.fjfokwiq.news.api;

/**
 * Created by fjfokwiq on 2017/5/1.
 */

public class ApiFactory {
    protected static final Object monitor  = new Object();

    private static NewsApi newsService;

    public static NewsApi getNewsService(){
        synchronized (monitor) {
            if (newsService == null) {
                newsService=new APiClient().getNewsService();
            }
        }
        return newsService;
    }

}
