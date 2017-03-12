package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.StartRunViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 23:02.
 * 类描述：
 */

public class StartRunFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new StartRunViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_start_run;
    }
}
