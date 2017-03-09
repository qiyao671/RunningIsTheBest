package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;

/**
 * Created by qiyao on 2017/3/3.
 */

public class BottomNavigationViewBindingAdapters {
    @BindingAdapter(value = "onNavigationItemSelected")
    public static void setListener(BottomNavigationView bottomNavigationView, BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
}
