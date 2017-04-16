package com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/16 21:32.
 * 类描述：
 */

public class WriteMomentImageItemViewModel implements IItemViewModel {
    public static final String TYPE_IMAGE = "TYPE_IMAGE";
    private Context context;
    public ObservableField<String> imageUri = new ObservableField<>();

    public WriteMomentImageItemViewModel(Context context, String imageUri) {
        this.context = context;
        this.imageUri.set(imageUri);
    }

    @Override
    public String getItemViewType() {
        return TYPE_IMAGE;
    }
}
