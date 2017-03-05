package com.qiyao.bysj.runningisthebest.module.login.viewmodel;

import android.databinding.ObservableField;

import com.google.gson.Gson;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.support.Constants;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 17:16.
 * 类描述：
 */

public class LoginViewModel implements IViewModel {
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public void login() {

    }

    public void launchRegisterActivity() {

    }

    public void saveUser(UserBean user) {
        AppApplication.instance().getSpUtils().putString(Constants.SP_KEY_USER_BEAN, new Gson().toJson(user));
    }
}
