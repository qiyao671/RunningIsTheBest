package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.AbsDataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunViewModel;

/**
 * Created by qiyao on 2017/3/7.
 */

public class RunFragment extends AbsDataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new RunViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_run;
    }
}
