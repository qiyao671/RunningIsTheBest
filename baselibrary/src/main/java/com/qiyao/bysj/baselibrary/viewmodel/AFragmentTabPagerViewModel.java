package com.qiyao.bysj.baselibrary.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.component.adapter.FragmentTabsPagerAdapter;
import com.qiyao.bysj.baselibrary.model.bean.FragmentTabItem;

import java.util.List;

/**
 * Created by qiyao on 2017/3/10.
 */

public abstract class AFragmentTabPagerViewModel extends BaseObservable implements IViewModel {
    @NonNull
    private Fragment fragment;

    @NonNull
    private FragmentTabsPagerAdapter adapter;
    @NonNull
    public ObservableField<List<FragmentTabItem>> items = new ObservableField<>();

    public AFragmentTabPagerViewModel(@NonNull Fragment fragment) {
        this.fragment = fragment;
        adapter = initAdapter();
        items.set(generateItems());
    }

    @Bindable
    public FragmentTabsPagerAdapter getAdapter() {
        return adapter;
    }

    private FragmentTabsPagerAdapter initAdapter() {
        return new FragmentTabsPagerAdapter(fragment.getFragmentManager(), fragment.getActivity());
    }

    protected abstract List<FragmentTabItem> generateItems();
}
