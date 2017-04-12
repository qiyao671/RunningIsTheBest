package com.qiyao.bysj.runningisthebest.component.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.qiyao.bysj.runningisthebest.R;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/12 22:05.
 * 类描述：
 */

public class SimpleNineGridImageViewAdapter extends NineGridImageViewAdapter<String> {
    @Override
    protected void onDisplayImage(Context context, ImageView imageView, String s) {
        Glide.with(imageView.getContext())
                .load(s)
                .placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.md_grey_300)))
                .crossFade()
                .dontAnimate()
                .into(imageView);
    }

}
