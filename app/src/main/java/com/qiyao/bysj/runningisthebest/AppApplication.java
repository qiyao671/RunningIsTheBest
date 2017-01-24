package com.qiyao.bysj.runningisthebest;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;

import com.qiyao.bysj.baselibrary.common.context.GlobalContext;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AppApplication extends GlobalContext {
    public static String secretKey = "";
    static final String t = "5218-ef562-ae01";
    // TODO: 2017/1/24 User
    private UserBean user;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            loadUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static AppApplication instance() {
        return (AppApplication) getInstance();
    }

    public void loadUser() {
        user = UserDB.getUser();
    }

    public static boolean hasLogged() {
        return instance().getUser() != null;
    }

    public UserBean getUser() {
        return user;
    }

    public String getClientUserSession() {
        if (user == null) {
            return "";
        }
        return user.getClientUserSession();
    }

    public String getUserToken() {
        if (user == null) {
            return "";
        }
        return user.getUserToken();
    }

}
