package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiyao.bysj.baselibrary.ui.activity.BaseActivity;
import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.BestRunViewModel;

/**
 * Created by qiyao on 2017/3/9.
 */

public class BestRunFragment extends ADataBindingFragment {
    private static final String KEY_USER_ID = "USER_ID";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initToolbar();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initToolbar() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.best_run);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        if (arguments.getInt(KEY_USER_ID, -1) != -1) {
            return new BestRunViewModel(this, arguments.getInt(KEY_USER_ID));
        }
        return new BestRunViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_best_run;
    }

    public static void launch(Activity from) {
        FragmentContainerActivity.launch(from, BestRunFragment.class);
    }

    public static void launch(Context context, Integer userId) {
        Bundle args = new Bundle();
        args.putInt(KEY_USER_ID, userId);
        FragmentContainerActivity.launch(context, BestRunFragment.class, args);
    }
}
