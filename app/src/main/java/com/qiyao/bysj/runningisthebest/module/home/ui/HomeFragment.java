package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.AbsDataBindingFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.HomeViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/1 22:22.
 * 类描述：
 */

public class HomeFragment extends AbsDataBindingFragment {
    @NonNull
    @Override
    protected HomeViewModel createViewModel() {
        return new HomeViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }
}
