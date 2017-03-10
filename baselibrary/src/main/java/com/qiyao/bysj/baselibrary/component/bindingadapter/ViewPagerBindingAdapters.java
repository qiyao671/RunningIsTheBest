package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.qiyao.bysj.baselibrary.component.adapter.FragmentTabsPagerAdapter;
import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;

import java.util.List;

/**
 * Created by qiyao on 2017/3/10.
 */

public class ViewPagerBindingAdapters {
    @BindingAdapter(value = {"fragmentPagerAdapter", "items"})
    public static void set(ViewPager viewPager, FragmentTabsPagerAdapter adapter ,List<FragmentTabItem> items) {
        adapter.setTabItems(items);
        viewPager.setAdapter(adapter);
    }
}
