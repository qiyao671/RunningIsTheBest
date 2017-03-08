package com.qiyao.bysj.runningisthebest.component.bindingadapter;

import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.qiyao.bysj.runningisthebest.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/3 21:44.
 * 类描述：
 */

public class CircleImageViewBindingAdapters {
    @BindingAdapter(value = "imageUrl")
    public static void loadImage(CircleImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .dontAnimate()
                .into(imageView);
    }
}
