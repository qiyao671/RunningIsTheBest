package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.net.HttpFactory;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import okhttp3.HttpUrl;
import okhttp3.Request;
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
//        addFixedParam();
        runApiService = getApiService(RunApiService.HOST, RunApiService.class);
    }

    public static HttpMethods getInstance() {
        if (INSTANCE == null) {
            return new HttpMethods();
        } else {
            return INSTANCE;
        }
    }

    private void addFixedParam() {
        getOkHttpClient().interceptors().add(chain -> {
            Request original = chain.request();

            HttpUrl url = original.url();

            // TODO: 2017/3/6 clientSession
            if (AppApplication.hasLogged()) {
                url = url.newBuilder()
//                        .addQueryParameter("token", AppApplication.instance().getUser().)
                        .build();
            }


            Request request = original
                    .newBuilder()
                    .url(url)
                    .build();

            return chain.proceed(request);
        });
    }

    public Observable<BestRunBean> getBestRun() {
        return handleResult(runApiService.getBestRun());
    }

    public Observable<String> login(UserBean user) {
        return handleResult(runApiService.login(user));
    }

    public Observable<String> login(String username, String password) {
        return handleResult(runApiService.login(username, password));
    }

    public Observable<UserBean> getUserBean(String token) {
        return handleResult(runApiService.getUser(token));
    }
}
