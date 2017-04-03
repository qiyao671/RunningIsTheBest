package com.qiyao.bysj.runningisthebest.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/3 21:44.
 * 类描述：
 */

public class CircleImageViewBindingAdapters {
    @BindingAdapter(value = {"imageUrl", "placeholderRes", "placeholder"}, requireAll = false)
    public static void loadImage(CircleImageView imageView, String url, int placeholderRes, Drawable placeholder) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .placeholder(placeholderRes)
                .crossFade()
                .dontAnimate()
                .into(imageView);
    }
}
