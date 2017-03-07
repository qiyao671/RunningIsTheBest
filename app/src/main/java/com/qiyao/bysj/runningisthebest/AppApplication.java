package com.qiyao.bysj.runningisthebest;

import com.google.gson.Gson;
import com.qiyao.bysj.baselibrary.common.context.GlobalContext;
import com.qiyao.bysj.baselibrary.common.utils.SPUtils;
import com.qiyao.bysj.baselibrary.common.utils.Utils;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.support.Constants;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AppApplication extends GlobalContext {
    public static String secretKey = "";
    static final String t = "5218-ef562-ae01";
    public final static String SP_NAME = "run";
    private UserBean user;
    private SPUtils spUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initUtils();
            initSPUtils();
            loadUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static AppApplication instance() {
        return (AppApplication) getInstance();
    }

    public void loadUser() {
        user = new Gson().fromJson(spUtils.getString(Constants.SP_KEY_USER_BEAN), UserBean.class);
    }

    public static boolean hasLogged() {
        return instance().getUser() != null;
    }

    public UserBean getUser() {
        return user;
    }

    public void initSPUtils() {
        spUtils = new SPUtils(SP_NAME);
    }

    public SPUtils getSpUtils() {
        return spUtils;
    }

    public void initUtils() {
        Utils.init(getApplicationContext());
    }

/*    public String getClientUserSession() {
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
    }*/
}
