package com.qiyao.bysj.runningisthebest;

import com.qiyao.bysj.baselibrary.common.context.GlobalContext;
import com.qiyao.bysj.baselibrary.common.utils.SPUtils;
import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.common.utils.Utils;
import com.qiyao.bysj.runningisthebest.common.Constants;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AppApplication extends GlobalContext {
    public static String secretKey = "";
    static final String t = "5218-ef562-ae01";
    public final static String SP_NAME = "run";
    private String token;
    private SPUtils spUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initUtils();
            initSPUtils();
            loadToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static AppApplication instance() {
        return (AppApplication) getInstance();
    }

    public void loadToken() {
        token = spUtils.getString(Constants.SP_KEY_USER_TOKEN);
    }

    public static boolean hasLogged() {
        return !StringUtils.isEmpty(instance().token);
    }

    public String getToken() {
        return token;
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
