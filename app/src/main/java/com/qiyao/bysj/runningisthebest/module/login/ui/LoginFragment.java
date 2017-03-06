package com.qiyao.bysj.runningisthebest.module.login.ui;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.AbsDataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.login.viewmodel.LoginViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/5 19:30.
 * 类描述：
 */

public class LoginFragment extends AbsDataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new LoginViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_login;
    }

    public static void launch(Activity from) {
        FragmentContainerActivity.launch(from, LoginFragment.class, null);
    }
}
