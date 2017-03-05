package com.qiyao.bysj.baselibrary.ui.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiyao.bysj.baselibrary.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.trello.rxlifecycle.components.RxFragment;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/1 22:24.
 * 类描述：
 */

public abstract class AbsFragment extends RxFragment {
    private IViewModel viewModel;
    private ViewDataBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }

    public IViewModel getViewModel() {
        return viewModel;
    }

    protected abstract IViewModel createViewModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false);
        if (getViewModel() != null) {
            binding.setVariable(BR.viewModel, viewModel);
        }
        return binding.getRoot();
    }

    protected abstract int layoutRes();
}
