package com.qiyao.bysj.runningisthebest.module.login.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.login.viewmodel.LoginViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 19:30.
 * 类描述：
 */

public class LoginFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new LoginViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_login;
    }

    public static void launchAndClearTask(Activity from) {
        int flags = Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK;
        FragmentContainerActivity.launch(from, LoginFragment.class, flags);
    }
}
