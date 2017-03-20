package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.model.net.HttpFactory;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.ListResultBean;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import org.w3c.dom.Comment;

import java.util.List;

import okhttp3.HttpUrl;
import rx.Observable;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/6 20:44.
 * 类描述：
 */

public class HttpMethods extends HttpFactory {
    private static HttpMethods INSTANCE;
    private RunApiService runApiService = null;

    private HttpMethods() {
        super();
        runApiService = getApiService(RunApiService.HOST, RunApiService.class);
    }

    @Override
    protected HttpUrl.Builder addFixedParam(HttpUrl.Builder builder) {
        String token = AppApplication.instance().getToken();
        if (AppApplication.hasLogged()) {
            return builder.addQueryParameter("token", token);
        }
        return super.addFixedParam(builder);
    }

    public static HttpMethods getInstance() {
        if (INSTANCE == null) {
            return INSTANCE = new HttpMethods();
        } else {
            return INSTANCE;
        }
    }

    public Observable<BestRunBean> getBestRun() {
        return handleResult(runApiService.getBestRun());
    }

    public Observable<String> login(String username, String password) {
        return handleResult(runApiService.login(username, password));
    }

/*    public Observable<UserBean> getUserBean(String token) {
        return handleResult(runApiService.getUser(token));
    }*/

    public Observable<UserBean> getUserBean() {
        return handleResult(runApiService.getUser());
    }

    public Observable<TotalRunBean> getTotalLogInfo() {
        return handleResult(runApiService.getTotalLogInfo());
    }

    public Observable<String> register(String userName, String password) {
        return handleResult(runApiService.register(userName, password));
    }

    public Observable<ListResultBean<UserBean>> getFriends() {
        return handleResult(runApiService.getFriends());
    }

    public Observable<List<UserBean>> getUsersByUserName(String username) {
        return handleResult(runApiService.getUsersByUserName(username));
    }

    public Observable<String> postMoment(MomentBean momentBean) {
        return handleResult(runApiService.postMoment(momentBean));
    }

    public Observable<List<MomentBean>> getRecentMoments(Integer minId, Integer maxId, Integer pageSize) {
        return handleResult(runApiService.getRecentMoments(minId, maxId, pageSize));
    }

    public Observable<String> updateUserInfo(UserBean userBean) {
        return handleResult(runApiService.updateUserInfo(userBean));
    }

    public Observable<String> deleteMoment(int momentId) {
        return handleResult(runApiService.deleteMoment(momentId));
    }

    public Observable<String> likeMoment(int momentId, boolean isLike) {
        return handleResult(runApiService.likeMoment(momentId, isLike));
    }

    public Observable<List<UserBean>> listApproveUsers(int momentId) {
        return handleResult(runApiService.listApproveUsers(momentId));
    }

    public Observable<String> deleteMoment(int momentId, Comment comment) {
        return handleResult(runApiService.commentMoment(momentId, comment));
    }
}
