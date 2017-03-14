package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.baselibrary.viewmodel.AFragmentTabPagerViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.TotalRunViewModel;

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
    }

    @Override
    protected List<FragmentTabItem> generateItems() {
        List<FragmentTabItem> items = new ArrayList<>();
        items.add(new FragmentTabItem(getString(R.string.week), TotalRunViewModel.TYPE_WEEK, TotalRunFragment.class));
        items.add(new FragmentTabItem(getString(R.string.month), TotalRunViewModel.TYPE_MONTH, TotalRunFragment.class));
        items.add(new FragmentTabItem(getString(R.string.total), TotalRunViewModel.TYPE_TOTAL, TotalRunFragment.class));
        return items;
    }

    public static void launch(Activity activity) {
        FragmentContainerActivity.launch(activity, TotalRunPagerFragment.class);
    }
}
