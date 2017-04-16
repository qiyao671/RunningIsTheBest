package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/16 21:34.
 * 类描述：
 */

public class WriteMomentAddImageItemViewModel implements IItemViewModel, View.OnClickListener {
    public static final String TYPE_ADD_IMAGE = "TYPE_ADD_IMAGE";
    private View.OnClickListener onClickListener;

    public WriteMomentAddImageItemViewModel(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public String getItemViewType() {
        return TYPE_ADD_IMAGE;
    }


    @Override
    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
