package com.example.fjfokwiq.news.api;


public class ApiFactory {
    protected static final Object monitor  = new Object();

    private static NewsApi newsService;

    private static LoginAPi loginService;

    public static NewsApi getNewsService(){
        synchronized (monitor) {
            if (newsService == null) {
                newsService=new APiClient().getNewsService();
            }
        }
        return newsService;
    }
    public static LoginAPi getLoginService(){
        synchronized (monitor) {
            if (loginService == null) {
                loginService=new APiClient().getLoginService();
            }
        }
        return loginService;
    }

}
