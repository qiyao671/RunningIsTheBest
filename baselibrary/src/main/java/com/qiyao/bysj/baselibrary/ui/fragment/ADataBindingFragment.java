package com.qiyao.bysj.baselibrary.ui.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public abstract class ADataBindingFragment extends ABaseFragment {
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

    @NonNull
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

    public ViewDataBinding getBinding() {
        return binding;
    }
}
