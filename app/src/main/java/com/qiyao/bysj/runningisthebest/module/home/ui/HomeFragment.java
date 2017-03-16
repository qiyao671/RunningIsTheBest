package com.qiyao.bysj.runningisthebest.module.home.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qiyao.bysj.baselibrary.model.event.MessageEvent;
import com.qiyao.bysj.baselibrary.model.event.RxBus;
import com.qiyao.bysj.baselibrary.ui.fragment.ADataBindingFragment;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.module.home.viewmodel.HomeViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/1 22:22.
 * 类描述：
 */

public class HomeFragment extends ADataBindingFragment {
    public static final String EVENT_EDIT_INFO = "EVENT_EDIT_INFO";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiveMessage();
    }

    @NonNull
    @Override
    protected HomeViewModel createViewModel(Bundle arguments) {
        return new HomeViewModel(this);
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_home;
    }


    private void receiveMessage() {
        RxBus.getDefault()
                .toObservable(MessageEvent.class)
                .subscribe(event -> {
                    if (event.getName().equals(EVENT_EDIT_INFO) && event.isSuccess()) {
                        ((HomeViewModel) getViewModel()).refreshMyInfo();
                    }
                });
    }

}
