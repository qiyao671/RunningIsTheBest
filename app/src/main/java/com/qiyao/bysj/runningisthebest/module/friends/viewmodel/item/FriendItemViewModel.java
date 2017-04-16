package com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.friends.ui.UserInfoFragment;

/**
 * Created by qiyao on 2017/3/10.
 */

public class FriendItemViewModel implements IItemViewModel {
    public static final String TYPE_FRIEND = "TYPE_FRIEND";

    private Context context;

    public ObservableField<String> profileUrl = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();

    private UserBean user;

    public FriendItemViewModel(Context context, UserBean user) {
        this.context = context;
        this.user = user;
        profileUrl.set(user.getProfile());
        userName.set(user.getUsername());
    }

    @Override
    public String getItemViewType() {
        return TYPE_FRIEND;
    }

    public void onClick(View v) {
        UserInfoFragment.launch(context, user);
    }

    public UserBean getUser() {
        return user;
    }

    public Context getContext() {
        return context;
    }
}
