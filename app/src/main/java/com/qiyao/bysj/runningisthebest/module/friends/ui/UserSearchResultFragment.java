package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.friends.viewmodel.UserSearchResultViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/12 22:56.
 * 类描述：
 */

public class UserSearchResultFragment extends ARecyclerViewFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getBinding().toolbar.setVisibility(View.GONE);
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        String query = arguments.getString(AddFriendActivity.KEY_QUERY);
        return new UserSearchResultViewModel(this, query);
    }

    public static Fragment newInstance(String query) {
        Fragment fragment = new UserSearchResultFragment();
        Bundle args = new Bundle();
        args.putString(AddFriendActivity.KEY_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }
}
