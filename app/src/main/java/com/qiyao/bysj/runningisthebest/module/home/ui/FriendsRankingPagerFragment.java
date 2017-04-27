package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.module.home.FriendsRankingFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qiyao on 2017/4/26.
 */

public class FriendsRankingPagerFragment extends AFragmentPagerFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTabLayout().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
        getTabLayout().setTabTextColors(ContextCompat.getColor(getActivity(), R.color.md_white_1000), ContextCompat.getColor(getActivity(), R .color.accent));
        setTitle(R.string.friend_ranking);
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected List<FragmentTabItem> generateItems() {
        return Arrays.asList(new FragmentTabItem(Constants.TYPE_DAY, getString(R.string.day), FriendsRankingFragment.class),
                new FragmentTabItem(Constants.TYPE_WEEK, getString(R.string.week), FriendsRankingFragment.class),
                new FragmentTabItem(Constants.TYPE_MONTH, getString(R.string.month), FriendsRankingFragment.class));
    }
}
