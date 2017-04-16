package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.WriteMomentViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/18 23:30.
 * 类描述：
 */

public class WriteMomentFragment extends ADataBindingFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setTitle(R.string.write_moment);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new WriteMomentViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_write_moment;
    }
}
