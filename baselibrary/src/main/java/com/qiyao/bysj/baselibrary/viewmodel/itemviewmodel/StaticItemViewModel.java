package com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel;

import android.view.View;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/2/10 22:18.
 * 类描述：
 */

public class StaticItemViewModel implements IItemViewModel,View.OnClickListener {
    private String type;
    public StaticItemViewModel(String type) {
        this.type = type;
    }

    public static final String TYPE_HEADER = "TYPE_HEADER";
    public static final String TYPE_FOOTER = "TYPE_FOOTER";
    public static final String TYPE_LOAD_MORE = "TYPE_LOAD_MORE";
    public static final String TYPE_EMPTY = "TYPE_EMPTY";
    public static final String TYPE_ITEM = "TYPE_ITEM";

    @Override
    public String getItemViewType() {
        return type;
    }

    @Override
    public void onClick(View view) {

    }
}
