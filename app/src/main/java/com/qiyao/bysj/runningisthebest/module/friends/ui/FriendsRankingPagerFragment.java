package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.ui.TotalRunFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 21:14.
 * 类描述：
 */

public class FriendsRankingPagerFragment extends AFragmentPagerFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTabLayout().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
    }

    @Override
    protected List<FragmentTabItem> generateItems() {
        List<FragmentTabItem> items = new ArrayList<>();
        items.add(new FragmentTabItem(getString(R.string.day), TotalRunFragment.class));
        items.add(new FragmentTabItem(getString(R.string.week), TotalRunFragment.class));
        items.add(new FragmentTabItem(getString(R.string.month), TotalRunFragment.class));
        return items;
    }
}
