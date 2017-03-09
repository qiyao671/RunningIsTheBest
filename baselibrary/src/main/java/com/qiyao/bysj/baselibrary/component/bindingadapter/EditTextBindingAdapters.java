package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.EditText;

/**
 * Created by qiyao on 2017/3/6.
 */

public class EditTextBindingAdapters {
    @BindingAdapter(value = "onFocusChange")
    public static void setOnFocusChange(EditText editText, View.OnFocusChangeListener listener) {
        editText.setOnFocusChangeListener(listener);
    }
}
