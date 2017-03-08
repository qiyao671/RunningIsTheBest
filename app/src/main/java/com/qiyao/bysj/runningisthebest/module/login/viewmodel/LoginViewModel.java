package com.qiyao.bysj.runningisthebest.module.login.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.util.Log;

import com.google.gson.Gson;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.login.ui.RegisterFragment;
import com.qiyao.bysj.runningisthebest.component.Constants;

import rx.schedulers.Schedulers;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 17:16.
 * 类描述：
 */

public class LoginViewModel implements IViewModel {
    private Fragment fragment;
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public LoginViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    public void login() {
        HttpMethods httpMethods = HttpMethods.getInstance();
        UserBean userBean = new UserBean();
        userBean.setUsername(userName.get());
        userBean.setPassword(password.get());
        httpMethods.login(userName.get(), password.get())
                .subscribeOn(Schedulers.newThread())
                .doOnNext(this::saveToken)
                .flatMap(httpMethods::getUserBean)
                .subscribe(this::saveUser, this::onLoginFailed);
    }

    public void launchRegisterActivity() {
        RegisterFragment.launch(fragment.getActivity());
    }

    private void saveUser(UserBean user) {
        AppApplication.instance().getSpUtils().putString(Constants.SP_KEY_USER_BEAN, new Gson().toJson(user));
    }

    private void onLoginFailed(Throwable e) {
        // TODO: 2017/3/6 fengzhuang
        Log.e("login", "onLoginFailed: " + e.getMessage());
    }

    private void saveToken(String token) {
        AppApplication.instance().getSpUtils().putString(Constants.SP_KEY_USER_TOKEN, token);
    }
}
