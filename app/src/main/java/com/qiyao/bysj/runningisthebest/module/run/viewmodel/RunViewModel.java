package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;

/**
 * Created by qiyao on 2017/3/7.
 */

public class RunViewModel implements IViewModel {
    private Fragment fragment;

    public ObservableField<String> distance = new ObservableField<>();
    public ObservableField<String> duration = new ObservableField<>();


}
