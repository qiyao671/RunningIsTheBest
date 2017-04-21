package com.qiyao.bysj.runningisthebest.module.run.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.BaseActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ARecyclerViewFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.run.viewmodel.RunRecordsViewModel;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsFragment extends ARecyclerViewFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideToolbar();
        initToolbar();
    }

    private void initToolbar() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.history);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new RunRecordsViewModel(this);
    }
}
