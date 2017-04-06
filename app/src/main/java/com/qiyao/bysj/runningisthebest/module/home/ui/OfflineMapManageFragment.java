package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.OfflineMapManageViewModel;

/**
 * Created by qiyao on 2017/4/5.
 */

public class OfflineMapManageFragment extends ARecyclerViewFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideToolbar();
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new OfflineMapManageViewModel(this);
    }
}
