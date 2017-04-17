package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.model.bean.HttpResult;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import org.w3c.dom.Comment;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/6 20:18.
 * 类描述：
 */

public interface RunApiService {
    String HOST = "http://192.168.31.245:8080/";
//    String HOST = "http://192.168.1.7:8080/";


    /**
     * 获得最佳记录
     * @return
     */
    @GET("log/getBestRunningLogInfo")
    Observable<HttpResult<BestRunBean>> getBestRun();

    @FormUrlEncoded
    @POST("user/login")
    Observable<HttpResult<String>> login(@Field("username") String username, @Field("password") String password);

    @GET("user/getUserInfoById")
    Observable<HttpResult<UserBean>> getUser();

    @GET("user/getUserInfoById")
    Observable<HttpResult<UserBean>> getUser(@Query("someOneId") int userId);

    @GET("log/getTotalLogInfo")
    Observable<HttpResult<TotalRunBean>> getTotalLogInfo(@Query("flag") int flag);

    @GET("log/listMyAllRunnyLogs")
    Observable<HttpResult<ListResultBean<RunBean>>> getMyRunRecords(@Query("num") int num, @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("user/add")
    Observable<HttpResult<String>> register(@Field("username") String username, @Field("password") String password);

    @GET("user/listFriends")
    Observable<HttpResult<List<UserBean>>> getFriends();

    @GET("user/listUsersByUsername")
    Observable<HttpResult<List<UserBean>>> getUsersByUserName(@Query("username") String username);

    @POST("moment/saveMoment")
    Observable<HttpResult<String>> postMoment(@Body MomentBean momentBean);

    @GET("moment/listNewestMoments")
    Observable<HttpResult<List<MomentBean>>> getRecentMoments(@Query("minId") Integer minId, @Query("maxId") Integer maxId, @Query("pageSize") Integer pageSize);

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

    @Multipart
    @POST("user/uploadFile")
    Observable<HttpResult<String>> uploadFile(@Part MultipartBody.Part profile);

    @FormUrlEncoded
    @POST("user/saveFriend")
    Observable<HttpResult<String>> addFriend(@Field("friendUserId") int userId);

    @FormUrlEncoded
    @POST("log/saveRunnyLog")
    Observable<HttpResult<String>> uploadRunRecord(@Body RunBean runnyLog);

    @GET("moment/listFriendsMoments")
    Observable<HttpResult<List<MomentBean>>> getFriendsRecentMoments(@Query("minId") Integer minId, @Query("maxId") Integer maxId, @Query("pageSize") Integer pageSize);

    @GET("user/listFriendRequests")
    Observable<HttpResult<List<UserBean>>> listFriendRequests();

    @FormUrlEncoded
    @POST("user/agreeAddFriend")
    Observable<HttpResult<String>> agreeAddFriend(@Field("friendUserId") int friendUserId);

    @POST("moment/saveMoment")
    Observable<HttpResult<String>> publishMoment(@Body RequestBody body);
}
