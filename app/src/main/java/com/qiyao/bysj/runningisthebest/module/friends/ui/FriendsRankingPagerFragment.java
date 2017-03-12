package com.qiyao.bysj.runningisthebest.module.friends.ui;

import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.baselibrary.viewmodel.AFragmentTabPagerViewModel;
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

    @NonNull
    @Override
    protected AFragmentTabPagerViewModel createViewModel() {
        return new AFragmentTabPagerViewModel(this) {
            @Override
            protected List<FragmentTabItem> generateItems() {
                List<FragmentTabItem> items = new ArrayList<>();
                items.add(new FragmentTabItem(getString(R.string.day), TotalRunFragment.class));
                items.add(new FragmentTabItem(getString(R.string.week), TotalRunFragment.class));
                items.add(new FragmentTabItem(getString(R.string.month), TotalRunFragment.class));
                return items;
            }
        };
    }
}
