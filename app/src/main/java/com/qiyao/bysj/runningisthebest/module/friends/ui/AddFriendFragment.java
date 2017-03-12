package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.AddFriendViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/12 22:56.
 * 类描述：
 */

public class AddFriendFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel() {
        return new AddFriendViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_add_friend;
    }
}
