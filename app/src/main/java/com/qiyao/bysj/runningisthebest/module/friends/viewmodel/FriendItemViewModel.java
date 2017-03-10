package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/10.
 */

public class FriendItemViewModel extends BaseObservable implements IItemViewModel, View.OnClickListener {
    public static final String TYPE_FRIEND = "TYPE_FRIEND";

    private Context context;

    private String profileUrl;
    private String userName;

    private UserBean user;

    public FriendItemViewModel(Context context, UserBean user) {
        this.context = context;
        this.user = user;
        profileUrl = user.getProfile();
        userName = user.getUsername();
    }

    @Override
    public String getItemViewType() {
        return TYPE_FRIEND;
    }

    @Bindable
    public String getProfileUrl() {
        return profileUrl;
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    @Override
    public void onClick(View v) {

    }
}
