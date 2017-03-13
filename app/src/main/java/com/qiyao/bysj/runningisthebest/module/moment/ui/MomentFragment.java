package com.qiyao.bysj.runningisthebest.module.moment.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.databinding.FragmentMomentBinding;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentViewModel;

/**
 * Created by qiyao on 2017/3/8.
 */

public class MomentFragment extends ADataBindingFragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = getBinding().toolbar;
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.tab_moment);
            activity.getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new MomentViewModel();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_moment;
    }

    @Override
    public FragmentMomentBinding getBinding() {
        return (FragmentMomentBinding) super.getBinding();
    }
}
