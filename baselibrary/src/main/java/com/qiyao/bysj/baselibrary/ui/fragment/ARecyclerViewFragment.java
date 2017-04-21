package com.qiyao.bysj.baselibrary.ui.fragment;

import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.databinding.FragmentRecyclerViewBinding;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

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
    public ACollectionViewModel getViewModel() {
        return (ACollectionViewModel) super.getViewModel();
    }

    public void hideToolbar() {
        ((FragmentRecyclerViewBinding)getBinding()).toolbar.setVisibility(View.GONE);
    }

    public Toolbar getToolbar() {
        return ((FragmentRecyclerViewBinding) getBinding()).toolbar;
    }
}
