package com.qiyao.bysj.baselibrary.support.bindinghelper;

import com.android.annotations.NonNull;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

/**
 * Created by qiyao on 2017/2/4.
 */

public interface IItemViewBindingCreator<T> {
    @NonNull ViewBindingRes genViewBindingRes();


    @NonNull
    IItemViewModel genItemViewModel(T item);
}
