package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

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
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new WriteMomentViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_write_moment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
