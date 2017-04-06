package com.qiyao.bysj.runningisthebest.module.home.viewmodel.item;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.EditMyInfoFragment;
import com.trello.rxlifecycle.components.RxFragment;

import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/15.
 */

public class MyInfoItemViewModel implements IItemViewModel {
    private Fragment fragment;

    private UserBean userBean;

    public ObservableField<String> userProfileUrl = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> userId = new ObservableField<>();
    public ObservableField<String> sex = new ObservableField<>();
    public ObservableField<String> age = new ObservableField<>();
    public ObservableField<String> constellation = new ObservableField<>();
    public ObservableField<String> signature = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String> height = new ObservableField<>();
    public ObservableField<String> bmi = new ObservableField<>("--");

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    public MyInfoItemViewModel(Fragment fragment) {
        this.fragment = fragment;
        //默认显示缓存的数据
        UserBean oldUser = SPHelper.loadUser();
        if (oldUser != null) {
            setUserInfo(oldUser);
        }
        getUser();
    }

    private void setUserInfo(UserBean user) {
        this.userBean = user;
        userProfileUrl.set(user.getProfile());
        userId.set(String.format(Locale.CHINA,"%06d", userBean.getId()));
        userName.set(user.getUsername());
        sex.set(user.getSex() == null ? fragment.getString(R.string.no_sex) : user.getSex());
        age.set(user.getAge() == null ? fragment.getString(R.string.no_age) : String.valueOf(user.getAge()) + "岁");
        constellation.set(user.getBirthday() == null ? "" : TimeUtils.getZodiac(user.getBirthday()));
        signature.set(user.getSignature());
        weight.set(user.getWeight() == null ? fragment.getString(R.string.no_weight) : String.valueOf(user.getWeight()) + "kg");
        height.set(user.getHeight() == null ? fragment.getString(R.string.no_height) : String.valueOf(user.getHeight()) + "cm");
        if (user.getWeight() != null && user.getHeight() != null
                && user.getHeight() != 0 && user.getWeight() != 0) {
            bmi.set(String.format(Locale.CHINA, "%.2f", user.getWeight() / (user.getHeight() * user.getHeight()) * 10000));
        }
    }

    private void getUser() {
        HttpMethods.getInstance()
                .getUserBean()
                .subscribeOn(Schedulers.newThread())
                .filter(user -> user != null)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(SPHelper::saveUser)
                .compose(((RxFragment) fragment).bindToLifecycle())
                .doOnNext(user -> userBean = user)
                .subscribe(this::setUserInfo, this::onError);
    }

    public void onClick(View view) {
        if (userBean != null) {
            EditMyInfoFragment.launch(fragment.getActivity(), userBean);
        }
    }

    private void onError(Throwable e) {

    }

    public void refreshData() {
        getUser();
    }
}
