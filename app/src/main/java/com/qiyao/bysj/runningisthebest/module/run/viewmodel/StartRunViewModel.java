package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunFragment;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 23:03.
 * 类描述：
 */

public class StartRunViewModel implements IViewModel {
    private Fragment fragment;

    public StartRunViewModel(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onClick(View v) {
        RunFragment.newInstance(fragment.getActivity());
    }
}
