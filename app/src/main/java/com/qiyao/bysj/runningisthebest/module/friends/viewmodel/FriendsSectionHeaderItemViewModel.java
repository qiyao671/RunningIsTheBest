package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.ASectionCollectionViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

/**
 * Created by qiyao on 2017/3/10.
 */

public class FriendsSectionHeaderItemViewModel implements IItemViewModel {
    public ObservableField<String> name = new ObservableField<>();

    FriendsSectionHeaderItemViewModel(String name) {
        this.name.set(name);
    }

    @Override
    public String getItemViewType() {
        return ASectionCollectionViewModel.TYPE_SECTION_HEADER;
    }
}
