package com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableInt;

import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/4/12.
 */

public class FriendRequestItemViewModel extends FriendItemViewModel {
    public ObservableInt status = new ObservableInt();
    public FriendRequestItemViewModel(Context context, UserBean user) {
        super(context, user);
        status.set(user.getRelationStatus());
    }

    public void acceptRequest() {

    }
}
