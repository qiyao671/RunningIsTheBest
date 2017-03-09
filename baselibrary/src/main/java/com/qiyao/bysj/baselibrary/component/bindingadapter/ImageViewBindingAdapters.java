package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by qiyao on 2017/2/16.
 */

public class ImageViewBindingAdapters {
    @BindingAdapter(value = "imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
//                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
    }
}
