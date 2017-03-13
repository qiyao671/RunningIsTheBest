package com.qiyao.bysj.baselibrary.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.databinding.FragmentRecyclerViewBinding;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:00.
 * 类描述：
 */

public abstract class ARecyclerViewFragment extends ADataBindingFragment {
    /*private FragmentRecyclerViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        if (getViewModel() != null) {
            binding.setViewModel(getViewModel());
        }
        return binding.getRoot();
    }*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().initItemViewModels();
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    public FragmentRecyclerViewBinding getBinding() {
        return (FragmentRecyclerViewBinding) super.getBinding();
    }

    @Override
    public ACollectionViewModel getViewModel() {
        return (ACollectionViewModel) super.getViewModel();
    }
}
