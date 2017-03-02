package com.qiyao.bysj.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/1 22:24.
 * 类描述：
 */

public abstract class AbsFragment<T extends IViewModel> extends RxFragment {
    private T viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }

    public T getViewModel() {
        return viewModel;
    }

    protected abstract T createViewModel();
}
