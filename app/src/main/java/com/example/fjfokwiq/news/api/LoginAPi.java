package com.example.fjfokwiq.news.api;

import com.example.fjfokwiq.news.bean.LoginMessage;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by okw on 2017/6/28 0028.
 */

public interface LoginAPi {


    /*
    登录
    http://192.168.116.2:8080/servlet/LoginServer
    */

    @FormUrlEncoded
    @POST("servlet/LoginData")
    Observable<LoginMessage> getToken(@Field("userInfo") String userInfo);

    /*
    * 注册
    * http://192.168.116.2:8080/servlet/RegisterServlet
    * */
    @FormUrlEncoded
    @POST("servlet/RegisterServlet")
    Observable<LoginMessage>userReg(@Field("registerInfo") String regInfo);

}
