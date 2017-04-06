package com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item;

import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/12 23:04.
 * 类描述：
 */

public class FriendsHeaderViewModel implements IItemViewModel {
    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_HEADER;
    }

    public void onClick(View view) {

    }
}
