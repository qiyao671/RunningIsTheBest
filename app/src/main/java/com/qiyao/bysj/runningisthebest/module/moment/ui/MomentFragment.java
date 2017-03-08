package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.AbsDataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentViewModel;

/**
 * Created by qiyao on 2017/3/8.
 */

public class MomentFragment extends AbsDataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new MomentViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_moment;
    }
}
