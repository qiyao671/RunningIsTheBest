package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.UserInfoViewModel;

/**
 * Created by qiyao on 2017/3/16.
 */

public class UserInfoFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new UserInfoViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return 0;
    }
}
