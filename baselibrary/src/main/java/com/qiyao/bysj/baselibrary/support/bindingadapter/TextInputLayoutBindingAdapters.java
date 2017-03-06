package com.qiyao.bysj.baselibrary.support.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

/**
 * Created by qiyao on 2017/3/6.
 */

public class TextInputLayoutBindingAdapters {
    @BindingAdapter(value = { "error"})
    public static void setError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }
}
