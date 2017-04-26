package com.qiyao.bysj.runningisthebest.module.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.common.CommonConstants;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.FriendRankingViewModel;

/**
 * Created by qiyao on 2017/4/26.
 */

public class FriendsRankingFragment extends ARecyclerViewFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        String type = getArguments().getString(CommonConstants.KEY_FRAGMENT_IN_PAGER_TYPE);
        return new FriendRankingViewModel(this, type);
    }
}
