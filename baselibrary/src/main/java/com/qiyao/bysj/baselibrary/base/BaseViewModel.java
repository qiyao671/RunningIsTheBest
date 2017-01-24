package com.qiyao.bysj.baselibrary.base;

import android.content.Context;

/**
 * Created by qiyao on 2017/1/24.
 */

public abstract class BaseViewModel implements IViewModel {
    private Context context;

    protected BaseViewModel(Context context) {
        this.context = context;
    }
}
