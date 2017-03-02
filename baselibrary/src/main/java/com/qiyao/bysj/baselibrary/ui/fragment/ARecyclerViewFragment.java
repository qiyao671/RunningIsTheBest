package com.qiyao.bysj.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiyao.bysj.baselibrary.databinding.FragmentRecyclerViewBinding;
import com.qiyao.bysj.baselibrary.viewmodel.ACollectionViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/1/29 12:00.
 * 类描述：
 */

public abstract class ARecyclerViewFragment extends AbsFragment<ACollectionViewModel> {
    private FragmentRecyclerViewBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false);
        if (getViewModel() != null) {
            binding.setViewModel(getViewModel());
        }
        return binding.getRoot();
    }

}
