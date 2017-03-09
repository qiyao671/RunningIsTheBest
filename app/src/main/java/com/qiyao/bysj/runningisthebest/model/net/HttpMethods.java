package com.qiyao.bysj.runningisthebest.model.net;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.model.net.HttpFactory;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.component.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

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
        String token = AppApplication.instance().getSpUtils().getString(Constants.SP_KEY_USER_TOKEN);
        if (AppApplication.hasLogged() && !StringUtils.isEmpty(token)) {
                    return builder.addQueryParameter("token", token);
        }
        return super.addFixedParam(builder);
    }

    public static HttpMethods getInstance() {
        if (INSTANCE == null) {
            return new HttpMethods();
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

    public Observable<UserBean> getUserBean(String token) {
        return handleResult(runApiService.getUser(token));
    }

    public Observable<UserBean> getUserBean() {
        return handleResult(runApiService.getUser());
    }

    public Observable<TotalRunBean> getTotalLogInfo() {
        return handleResult(runApiService.getTotalLogInfo());
    }
}
