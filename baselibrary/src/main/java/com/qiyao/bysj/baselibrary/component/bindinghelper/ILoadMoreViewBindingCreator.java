package com.qiyao.bysj.baselibrary.component.bindinghelper;

import android.support.annotation.NonNull;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/5 21:02.
 * 类描述：
 */

public interface ILoadMoreViewBindingCreator extends IItemViewBindingCreator<Object> {
    @NonNull
    ALoadMoreViewModel genItemViewModel(Object item);
}
