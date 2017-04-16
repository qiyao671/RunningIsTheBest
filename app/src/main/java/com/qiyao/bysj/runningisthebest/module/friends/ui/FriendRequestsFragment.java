package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.FriendRequestsViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/15 21:32.
 * 类描述：
 */

public class FriendRequestsFragment extends ARecyclerViewFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.friend_request);
        hideToolbar();
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new FriendRequestsViewModel(this);
    }
}
