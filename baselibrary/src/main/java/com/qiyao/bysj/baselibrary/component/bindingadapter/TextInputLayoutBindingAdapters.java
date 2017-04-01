package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;

/**
 * Created by qiyao on 2017/3/6.
 */

public class TextInputLayoutBindingAdapters {
    @BindingAdapter(value = { "error", "errorEnabled"})
    public static void setError(TextInputLayout textInputLayout, String error, boolean isErrorEnabled) {
        textInputLayout.setError(error);
        textInputLayout.setErrorEnabled(isErrorEnabled);
    }
}
