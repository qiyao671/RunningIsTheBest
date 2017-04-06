package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;
import com.qiyao.bysj.baselibrary.ui.fragment.AFragmentPagerFragment;
import com.qiyao.bysj.runningisthebest.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapPagerFragment extends AFragmentPagerFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.offline_map);
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected List<FragmentTabItem> generateItems() {
        return Arrays.asList(new FragmentTabItem(getString(R.string.city_list), OfflineMapDownloadFragment.class),
                new FragmentTabItem(getString(R.string.download_manage), OfflineMapManageFragment.class));
    }
}
