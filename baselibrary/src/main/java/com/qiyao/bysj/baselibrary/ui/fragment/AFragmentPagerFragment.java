package com.qiyao.bysj.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.databinding.FragmentTabPagerBinding;
import com.qiyao.bysj.baselibrary.viewmodel.AFragmentTabPagerViewModel;

/**
 * Created by qiyao on 2017/3/10.
 */

public abstract class AFragmentPagerFragment extends ADataBindingFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentTabPagerBinding binding = (FragmentTabPagerBinding) getBinding();
        TabLayout tabLayout = binding.tabLayout;
        ViewPager viewPager = binding.viewPager;
        tabLayout.setupWithViewPager(viewPager);
    }

    @NonNull
    @Override
    protected abstract AFragmentTabPagerViewModel createViewModel();

    @Override
    protected int layoutRes() {
        return R.layout.fragment_tab_pager;
    }
}
