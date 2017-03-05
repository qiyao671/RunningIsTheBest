package com.qiyao.bysj.baselibrary.support.bindinghelper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.qiyao.bysj.baselibrary.BR;
import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.SimpleLoadMoreViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/10 21:46.
 * 类描述：
 */

public class SimpleLoadMoreViewBindingCreator implements IItemViewBindingCreator<Object> {
    private Context context;
    public SimpleLoadMoreViewBindingCreator(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewBindingRes genViewBindingRes() {
        return new ViewBindingRes(R.layout.item_simple_load_more, BR.viewModel);
    }

    @NonNull
    @Override
    public SimpleLoadMoreViewModel genItemViewModel(Object item) {
        return new SimpleLoadMoreViewModel(context);
    }

}
