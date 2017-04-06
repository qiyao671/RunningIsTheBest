package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.OfflineMapDownloadViewModel;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapDownloadFragment extends ARecyclerViewFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideToolbar();
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new OfflineMapDownloadViewModel(this);
    }
}
