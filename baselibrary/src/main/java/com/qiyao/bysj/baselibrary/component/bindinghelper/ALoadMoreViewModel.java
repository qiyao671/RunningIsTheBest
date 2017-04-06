package com.qiyao.bysj.baselibrary.component.bindinghelper;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/4/5 20:55.
 * 类描述：
 */

public abstract class ALoadMoreViewModel implements IItemViewModel {
    public abstract void loadMore();
    public abstract void noMore();
    public abstract void isLoading();
    public abstract void loadFailed();

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_LOAD_MORE;
    }
}
