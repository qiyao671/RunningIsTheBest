package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.common.CommonConstants;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.TotalRunViewModel;

/**
 * Created by qiyao on 2017/3/10.
 */

public class TotalRunFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        String type = getArguments().getString(CommonConstants.KEY_FRAGMENT_IN_PAGER_TYPE);
        return new TotalRunViewModel(this, type);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_total_run;
    }
}
