package com.qiyao.bysj.runningisthebest.component.bindingadapter;

import android.databinding.BindingAdapter;

import com.qiyao.bysj.runningisthebest.widget.NineImageGridLayout;

import java.util.List;

/**
 * Created by qiyao on 2017/3/8.
 */

public class NineImageGridLayoutBindingAdapters {
    @BindingAdapter(value = {"imageUrls", "onChildClick"})
    public static void set(NineImageGridLayout layout, List<String> imageUrls, NineImageGridLayout.OnChildClickListener onChildClickListener) {
        layout.setImages(imageUrls);
        layout.setOnChildClickListener(onChildClickListener);
    }

    @BindingAdapter(value = "gap")
    public static void set(NineImageGridLayout layout, int gap) {
        layout.setGap(gap);
    }
}
