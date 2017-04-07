package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentOfflineMapPagerBinding;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.OfflineMapPagerViewModel;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapPagerFragment extends ADataBindingFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = getBinding().viewPager;
        TabLayout tabLayout = getBinding().tabLayout;
        tabLayout.setupWithViewPager(viewPager);
        setTitle(R.string.offline_map);
        setDisplayHomeAsUpEnabled(true);
        viewPager.addOnPageChangeListener((OfflineMapPagerViewModel)getViewModel());
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new OfflineMapPagerViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_offline_map_pager;
    }

    @Override
    public FragmentOfflineMapPagerBinding getBinding() {
        return (FragmentOfflineMapPagerBinding) super.getBinding();
    }
}
