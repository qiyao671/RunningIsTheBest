package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.model.bean.HttpResult;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import org.w3c.dom.Comment;

import java.util.List;

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
    @GET("log/getBestRunningLogInfo")
    Observable<HttpResult<BestRunBean>> getBestRun();

    @FormUrlEncoded
    @POST("user/login")
    Observable<HttpResult<String>> login(@Field("username") String username, @Field("password") String password);

    @GET("user/getUserInfo")
    Observable<HttpResult<UserBean>> getUser(String token);

    @GET("user/getUserInfo")
    Observable<HttpResult<UserBean>> getUser();

    @GET("log/getTotalLogInfo")
    Observable<HttpResult<TotalRunBean>> getTotalLogInfo();

    @FormUrlEncoded
    @POST("user/add")
    Observable<HttpResult<String>> register(@Field("username") String username, @Field("password") String password);

    @GET("user/listFriends")
    Observable<HttpResult<ListResultBean<UserBean>>> getFriends();

    @GET("user/listUsersByUsername")
    Observable<HttpResult<List<UserBean>>> getUsersByUserName(@Query("username") String username);

    @POST("moment/saveMoment")
    Observable<HttpResult<String>> postMoment(@Body MomentBean momentBean);

    @GET("moment/listNewestMoments")
    Observable<HttpResult<List<MomentBean>>> getRecentMoments(@Query("minId") int minId, @Query("maxId") int maxId, @Query("pageSize") Integer pageSize);

    @POST("user/update")
    Observable<HttpResult<String>> updateUserInfo(@Body UserBean userBean);

    @FormUrlEncoded
    @POST("moment/deleteMoment")
    Observable<HttpResult<String>> deleteMoment(@Field("momentId") int momentId);

    @FormUrlEncoded
    @POST("moment/approve")
    Observable<HttpResult<String>> likeMoment(@Field("momentId") int momentId, @Field("isApprove") boolean isApprove);

    @FormUrlEncoded
    @POST("moment/listApproveUser")
    Observable<HttpResult<List<UserBean>>> listApproveUsers(@Field("momentId") int momentId);

    @FormUrlEncoded
    @POST("moment/comment")
    Observable<HttpResult<String>> commentMoment(@Field("momentId") int momentId, @Field("comment") Comment comment);
}
