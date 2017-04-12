package com.qiyao.bysj.runningisthebest.component.bindingadapter;

import android.databinding.BindingAdapter;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.qiyao.bysj.runningisthebest.component.adapter.SimpleNineGridImageViewAdapter;

import java.util.List;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/12 22:18.
 * 类描述：
 */

public class NineGridImageViewBindingAdapters {
    @BindingAdapter(value = {"imageUrls", "onItemImageClickListener", "adapter"}, requireAll = false)
    public static void setup(NineGridImageView<String> nineGridImageView, List<String> imagesData, ItemImageClickListener<String> listener, NineGridImageViewAdapter<String> adapter) {
        if (adapter == null) {
            adapter = new SimpleNineGridImageViewAdapter();
        }
        nineGridImageView.setAdapter(adapter);
        nineGridImageView.setImagesData(imagesData);
        nineGridImageView.setItemImageClickListener(listener);
    }
}
