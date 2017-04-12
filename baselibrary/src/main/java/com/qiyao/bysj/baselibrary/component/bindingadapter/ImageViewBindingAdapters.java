package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by qiyao on 2017/2/16.
 */

public class ImageViewBindingAdapters {
    @BindingAdapter(value = {"imageUrl", "placeholderRes", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, int placeholderRes, Drawable placeholder) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .placeholder(placeholderRes)
                .crossFade()
                .dontAnimate()
                .into(imageView);
    }
}
