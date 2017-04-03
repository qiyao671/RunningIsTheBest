package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyao on 2017/3/10.
 */

public class TotalRunPagerFragment extends AFragmentPagerFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.sports_statistics);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getTabLayout().setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.primary));
        getTabLayout().setTabTextColors(ContextCompat.getColor(getActivity(), R.color.md_white_1000), ContextCompat.getColor(getActivity(), R .color.accent));
    }

    @Override
    protected List<FragmentTabItem> generateItems() {
        List<FragmentTabItem> items = new ArrayList<>();
        items.add(new FragmentTabItem(Constants.TYPE_WEEK, getString(R.string.week), TotalRunFragment.class));
        items.add(new FragmentTabItem(Constants.TYPE_MONTH, getString(R.string.month), TotalRunFragment.class));
        items.add(new FragmentTabItem(Constants.TYPE_TOTAL, getString(R.string.total), TotalRunFragment.class));
        return items;
    }

    public static void launch(Activity activity) {
        FragmentContainerActivity.launch(activity, TotalRunPagerFragment.class);
    }
}
