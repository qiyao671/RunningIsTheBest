package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunDetailViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:19.
 * 类描述：
 */

public class RunDetailFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new RunDetailViewModel();
    }

    @Override
    protected int layoutRes() {
        return 0;
    }
}
