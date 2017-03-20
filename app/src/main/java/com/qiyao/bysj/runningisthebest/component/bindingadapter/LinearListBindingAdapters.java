package com.qiyao.bysj.runningisthebest.component.bindingadapter;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.annotation.LayoutRes;

import com.linearlistview.LinearListView;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.BindingListViewAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * Created by qiyao on 2017/3/17.
 */

public class LinearListBindingAdapters {
    @SuppressWarnings("unchecked")
    @BindingAdapter(value = {"itemBinding", "itemTypeCount", "items", "adapter", "itemDropDownLayout", "itemIds", "itemIsEnabled"}, requireAll = false)
    public static <T> void setAdapter(LinearListView linearListView, ItemBinding<T> itemBinding, Integer itemTypeCount, List items, BindingListViewAdapter<T> adapter, @LayoutRes int itemDropDownLayout, BindingListViewAdapter.ItemIds<? super T> itemIds, BindingListViewAdapter.ItemIsEnabled<? super T> itemIsEnabled) {
        if (itemBinding == null) {
            throw new IllegalArgumentException("onItemBind must not be null");
        }
        BindingListViewAdapter<T> oldAdapter = (BindingListViewAdapter<T>) linearListView.getAdapter();
        if (adapter == null) {
            if (oldAdapter == null) {
                int count = itemTypeCount != null ? itemTypeCount : 1;
                adapter = new BindingListViewAdapter<>(count);
            } else {
                adapter = oldAdapter;
            }
        }
        adapter.setItemBinding(itemBinding);
        adapter.setDropDownItemLayout(itemDropDownLayout);
        adapter.setItems(items);
        adapter.setItemIds(itemIds);
        adapter.setItemIsEnabled(itemIsEnabled);

        if (oldAdapter != adapter) {
            linearListView.setAdapter(adapter);
        }
    }

    @BindingConversion
    public static <T> ItemBinding<T> toItemBinding(OnItemBind<T> onItemBind) {
        return ItemBinding.of(onItemBind);
    }
}