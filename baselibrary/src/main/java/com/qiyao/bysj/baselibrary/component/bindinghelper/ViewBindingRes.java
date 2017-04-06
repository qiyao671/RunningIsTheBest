package com.qiyao.bysj.baselibrary.component.bindinghelper;

import android.support.annotation.LayoutRes;

/**
 * Created by qiyao on 2017/2/3.
 */

public class ViewBindingRes {
    private int layoutRes;
    private int bindingVariableRes;

    public ViewBindingRes(@LayoutRes int layoutRes, int bindingVariableRes) {
        this.layoutRes = layoutRes;
        this.bindingVariableRes = bindingVariableRes;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public void setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
    }

    public int getBindingVariableRes() {
        return bindingVariableRes;
    }

    public void setBindingVariableRes(int bindingVariableRes) {
        this.bindingVariableRes = bindingVariableRes;
    }
}
