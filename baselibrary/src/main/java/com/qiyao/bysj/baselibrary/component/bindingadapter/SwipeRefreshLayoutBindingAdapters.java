package com.qiyao.bysj.baselibrary.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by qiyao on 2017/2/4.
 */

public class SwipeRefreshLayoutBindingAdapters {
    @BindingAdapter(value = {"onRefresh", "refreshEnable", "refreshing"}, requireAll = false)
    public static void setupRefresh(SwipeRefreshLayout swipeRefreshLayout, SwipeRefreshLayout.OnRefreshListener listener, boolean isRefreshEnable, boolean isRefreshing) {
        if (listener == null) {
            isRefreshEnable = false;
        } else {
            swipeRefreshLayout.setOnRefreshListener(listener);
        }
        swipeRefreshLayout.setEnabled(isRefreshEnable);
        swipeRefreshLayout.setRefreshing(isRefreshEnable && isRefreshing);
    }
}
