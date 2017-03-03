package com.qiyao.bysj.baselibrary.support.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;

/**
 * Created by qiyao on 2017/3/3.
 */

public class BottomNavigationViewAdapters {
    @BindingAdapter(value = "onNavigationItemSelected")
    public static void setListener(BottomNavigationView bottomNavigationView, BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }
}
