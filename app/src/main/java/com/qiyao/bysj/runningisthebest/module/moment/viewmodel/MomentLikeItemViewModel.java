package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentLikeItemViewModel extends BaseObservable implements IItemViewModel {
    private Context context;

    private UserBean userBean;

    private String userProfile;

    public MomentLikeItemViewModel(Context context, UserBean userBean) {
        this.context = context;
        this.userBean = userBean;
        userProfile = userBean.getProfile();
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    @Bindable
    public String getUserProfile() {
        return userProfile;
    }
}
