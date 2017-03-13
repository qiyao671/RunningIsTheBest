package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/13.
 */

public class UserSearchResultItemViewModel extends FriendItemViewModel implements IItemViewModel {

    public UserSearchResultItemViewModel(Context context, UserBean user) {
        super(context, user);
    }

    @Override
    public String getItemViewType() {
        return null;
    }

    public void onAddClick() {

    }
}
