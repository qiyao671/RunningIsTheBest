package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.ASectionCollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

/**
 * Created by qiyao on 2017/3/10.
 */

public class FriendsSectionHeaderItemViewModel extends BaseObservable implements IItemViewModel {
    private String name;

    FriendsSectionHeaderItemViewModel(String name) {
        this.name = name;
    }

    @Override
    public String getItemViewType() {
        return ASectionCollectionViewModel.TYPE_SECTION_HEADER;
    }

    @Bindable
    public String getName() {
        return name;
    }
}
