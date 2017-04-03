package com.qiyao.bysj.runningisthebest.module.friends.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/16.
 */

public class UserInfoViewModel implements IViewModel, View.OnClickListener {
    private Fragment fragment;

    private UserBean userBean;

    public ObservableField<String> profile = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userId = new ObservableField<>();
    public ObservableField<String> location = new ObservableField<>();
    public ObservableField<String> sex = new ObservableField<>();
    public ObservableField<String> rank = new ObservableField<>();
    public ObservableField<Double> totalDistance = new ObservableField<>();
    public ObservableField<Long> totalDuration = new ObservableField<>();
    public ObservableField<Boolean> isFriend = new ObservableField<>();

    public UserInfoViewModel(Fragment fragment, UserBean user) {
        this.fragment = fragment;
        this.userBean = user;
        setUserInfo(user);
        getUser(user.getId());
    }

    private void getUser(int userId) {
        HttpMethods.getInstance()
                .getUserBean(userId)
                .subscribeOn(Schedulers.newThread())
                .compose(((RxFragment) fragment).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setUserInfo, e -> ToastUtils.showLongToast(e.getMessage()));
    }

    private void setUserInfo(UserBean user) {
        if (user == null) {
            return;
        }
        profile.set(user.getProfile());
        userName.set(user.getUsername());
        userId.set(String.format(Locale.CHINA, "%06d", user.getId()));
        location.set(user.getLocation());
        sex.set(user.getSex());
//        rank.set();
//        totalDistance.set();

    }

    @Override
    public void onClick(View view) {

    }
}
