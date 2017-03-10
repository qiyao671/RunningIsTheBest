package com.qiyao.bysj.runningisthebest.common;

import com.google.gson.Gson;
import com.qiyao.bysj.baselibrary.common.utils.SPUtils;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/10.
 */

public class SPHelper {

    private static SPUtils spUtils = AppApplication.instance().getSpUtils();

    public static void saveUser(UserBean user) {
        spUtils.putString(Constants.SP_KEY_USER_BEAN, new Gson().toJson(user));
    }

    public static UserBean loadUser() {
        return new Gson().fromJson(spUtils.getString(Constants.SP_KEY_USER_BEAN), UserBean.class);
    }
}
