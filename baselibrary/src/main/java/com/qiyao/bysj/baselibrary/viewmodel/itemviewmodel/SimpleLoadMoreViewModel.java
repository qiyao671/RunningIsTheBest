package com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.StringRes;

import com.qiyao.bysj.baselibrary.R;
import com.qiyao.bysj.baselibrary.component.bindinghelper.ALoadMoreViewModel;


/**
 * Created by qiyao on 2017/2/6.
 */

public class SimpleLoadMoreViewModel extends ALoadMoreViewModel {
    //context
    private Context context;
    public final ObservableField<String> text = new ObservableField<>();


    public SimpleLoadMoreViewModel(Context context) {
        this.context = context;
        noMore();
    }

    public void loadMore() {
        text.set(getString(R.string.load_more));
    }

    public void noMore() {
        text.set(getString(R.string.no_more));
    }

    public void isLoading() {
        text.set(getString(R.string.is_loading));
    }

    public void loadFailed() {
        text.set(getString(R.string.load_failed));
    }

    private String getString(@StringRes int strRes) {
        return context.getString(strRes);
    }
}
