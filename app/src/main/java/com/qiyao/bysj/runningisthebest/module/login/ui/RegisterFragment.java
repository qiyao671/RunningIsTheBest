package com.qiyao.bysj.runningisthebest.module.login.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyao.bysj.baselibrary.ui.activity.FragmentContainerActivity;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.login.viewmodel.RegisterViewModel;

/**
 * Created by qiyao on 2017/3/6.
 */

public class RegisterFragment extends ADataBindingFragment {
    @NonNull
    @Override
    protected IViewModel createViewModel(Bundle arguments) {
        return new RegisterViewModel(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity.getSupportActionBar() != null) {
            activity.setTitle(getString(R.string.register));
        }
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_register;
    }

    public static void launch(Activity from) {
        FragmentContainerActivity.launch(from, RegisterFragment.class);
    }
}
