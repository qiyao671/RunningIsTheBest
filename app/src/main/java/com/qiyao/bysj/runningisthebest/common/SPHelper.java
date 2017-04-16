package com.qiyao.bysj.runningisthebest.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiyao.bysj.baselibrary.common.utils.SPUtils;
import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.runningisthebest.AppApplication;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

import java.util.List;

/**
 * Created by qiyao on 2017/3/10.
 */

public class SPHelper {
    public final static String SP_KEY_USER_BEAN = "user";
    public final static String SP_KEY_USER_TOKEN = "token";
    public final static String SP_KEY_FRIEND_LIST = "friends";

    private static SPUtils spUtils = AppApplication.instance().getSpUtils();

    public static void saveUser(UserBean user) {
        spUtils.putString(SP_KEY_USER_BEAN, new Gson().toJson(user));
    }

    public static UserBean loadUser() {
        return new Gson().fromJson(spUtils.getString(SP_KEY_USER_BEAN), UserBean.class);
    }

    public static void saveToken(String token) {
        spUtils.putString(SP_KEY_USER_TOKEN, token);
    }

    public static String loadToken() {
        return spUtils.getString(SP_KEY_USER_TOKEN);
    }

    public static void removeToken() {
        spUtils.remove(SP_KEY_USER_TOKEN);
    }

    public static void removeUser() {
        spUtils.remove(SP_KEY_USER_BEAN);
    }

    public static void saveFriends(List<UserBean> friends) {
        spUtils.putString(SP_KEY_FRIEND_LIST, new Gson().toJson(friends));
    }

    public static List<UserBean> loadFriends() {
        String jsonStr = spUtils.getString(SP_KEY_FRIEND_LIST);
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        return new Gson().fromJson(jsonStr, new TypeToken<List<UserBean>>(){}.getType());
    }

    public static void removeFriends() {
        spUtils.remove(SP_KEY_FRIEND_LIST);
    }
}
