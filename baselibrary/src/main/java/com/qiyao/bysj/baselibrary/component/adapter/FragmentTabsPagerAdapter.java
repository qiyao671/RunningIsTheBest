package com.qiyao.bysj.baselibrary.component.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.common.CommonConstants;
import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyao on 2017/3/9.
 */

public class FragmentTabsPagerAdapter extends FragmentPagerAdapter {

    @NonNull
    private List<FragmentTabItem> tabItems = new ArrayList<>();
    @NonNull
    private Context context;
    public FragmentTabsPagerAdapter(FragmentManager fm, @NonNull Context context) {
        super(fm);
        this.context = context;
    }

    public FragmentTabsPagerAdapter(FragmentManager fm, @NonNull Context context, @NonNull List<FragmentTabItem> tabItems) {
        this(fm, context);
        this.tabItems = tabItems;
    }

    @Override
    public Fragment getItem(int position) {
        return newFragment(tabItems.get(position));
    }

    private Fragment newFragment(FragmentTabItem item) {
        String name = item.getFragmentClass().getName();
        Bundle args = new Bundle();
        args.putParcelable(CommonConstants.KEY_FRAGMENT_IN_PAGER_ARGS, item.getTag());
        return Fragment.instantiate(context, name, args);
    }

    @Override
    public String makeFragmentName(int position) {
        return "Tab_" + tabItems.get(position).getFragmentClass().getName();
    }

    @Override
    public int getCount() {
        return tabItems.size();
    }

    public void setTabItems(@NonNull List<FragmentTabItem> tabItems) {
        this.tabItems = tabItems;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabItems.get(position).getTitle();
    }
}
