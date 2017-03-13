package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.HomeViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/1 22:22.
 * 类描述：
 */

public class HomeFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected HomeViewModel createViewModel(Bundle arguments) {
        return new HomeViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }
}
