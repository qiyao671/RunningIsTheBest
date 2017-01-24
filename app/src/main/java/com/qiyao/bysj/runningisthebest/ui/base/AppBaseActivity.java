package com.qiyao.bysj.runningisthebest.ui.base;

import android.os.Bundle;

import com.qiyao.bysj.baselibrary.base.BaseActivity;
import com.qiyao.bysj.runningisthebest.AppApplication;

/**
 * Created by qiyao on 2017/1/24.
 */

public class AppBaseActivity extends BaseActivity {

    @Override
    public UserBean getUser() {
        return AppApplication.instance().getUser();
    }

    @Override
    public boolean hasLogged() {
        return AppApplication.hasLogged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            //确保加载框关闭
            hideLoading();
            //确保弹框关闭,如果activity关闭后,dialog没关闭的话,会报错has leaked window that was originally added
            DialogHelper.dismissDialog(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
