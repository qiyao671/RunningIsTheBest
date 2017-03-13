package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.SettingViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 14:36.
 * 类描述：
 */

public class SettingFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new SettingViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_setting;
    }

    public static void launch(Activity activity) {
        FragmentContainerActivity.launch(activity, SettingFragment.class);
    }
}
