package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

/**
 * Created by qiyao on 2017/2/16.
 */

public class ImageViewBindingAdapters {
    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, int placeholderRes) {
        DrawableTypeRequest<String> request = Glide.with(imageView.getContext())
                .load(url);
        if (placeholderRes != 0) {
            request.placeholder(placeholderRes);
        }
        request
                .crossFade()
                .into(imageView);
    }
}
