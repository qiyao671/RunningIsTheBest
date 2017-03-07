package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.net.HttpResult;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/6 20:18.
 * 类描述：
 */

public interface RunApiService {
    String HOST = "http://192.168.1.7:8080/";


    /**
     * 获得最佳记录
     * @return
     */
    @GET("log/getBestRunningLogInfo?token=1_ff95fc28c94b78df96001695d4d6d0b0")
    Observable<HttpResult<BestRunBean>> getBestRun();


    @POST("user/login")
    Observable<HttpResult<String>> login(@Body UserBean user);

    @FormUrlEncoded
    @POST("user/login")
    Observable<HttpResult<String>> login(@Field("username") String username, @Field("password") String password);

    @GET("user/getUserInfo")
    Observable<HttpResult<UserBean>> getUser(@Query("token") String token);
}
