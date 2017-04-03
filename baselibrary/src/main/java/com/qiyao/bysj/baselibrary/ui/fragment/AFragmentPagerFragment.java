package com.qiyao.bysj.baselibrary.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.component.adapter.FragmentTabsPagerAdapter;
import com.qiyao.bysj.baselibrary.databinding.FragmentTabPagerBinding;
import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;

import java.util.List;

/**
 * Created by qiyao on 2017/3/10.
 */

public abstract class AFragmentPagerFragment extends ABaseFragment {

    private FragmentTabPagerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_pager, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = binding.viewPager;
        TabLayout tabLayout = binding.tabLayout;
        viewPager.setAdapter(new FragmentTabsPagerAdapter(getChildFragmentManager(), getActivity(), generateItems()));
        tabLayout.setupWithViewPager(viewPager);
    }

    protected abstract List<FragmentTabItem> generateItems();

    public TabLayout getTabLayout() {
        return binding.tabLayout;
    }

}
