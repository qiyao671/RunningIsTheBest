package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.baselibrary.viewmodel.AFragmentTabPagerViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.module.home.ui.TotalRunFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:09.
 * 类描述：
 */

public class RunRecordDetailPagerFragment extends AFragmentPagerFragment {
    private static final String KEY_RUN_BEAN = "runBean";
    @Override
    protected List<FragmentTabItem> generateItems() {
        RunBean runBean = getArguments().getParcelable(KEY_RUN_BEAN);
        List<FragmentTabItem> items = new ArrayList<>();
        items.add(new FragmentTabItem(getString(R.string.track), runBean, RunTrackFragment.class));
        items.add(new FragmentTabItem(getString(R.string.detail), runBean, RunDetailFragment.class));
        return items;
    }

    public static void launch(Activity activity, RunBean runBean) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_RUN_BEAN, runBean);
        FragmentContainerActivity.launch(activity, RunRecordDetailPagerFragment.class, bundle);
    }
}
