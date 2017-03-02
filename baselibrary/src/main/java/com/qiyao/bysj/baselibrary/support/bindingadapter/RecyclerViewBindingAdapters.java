package com.qiyao.bysj.baselibrary.support.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qiyao.bysj.baselibrary.support.bindinghelper.OnLoadMoreListener;


/**
 * Created by qiyao on 2017/2/6.
 */

public class RecyclerViewBindingAdapters {
    @BindingAdapter(value = "onLoadMore")
    public static void onScroll(RecyclerView recyclerView, final OnLoadMoreListener onLoadMoreListener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }
}
