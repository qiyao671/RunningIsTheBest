package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.EditMyInfoFragment;
import com.trello.rxlifecycle.components.RxFragment;

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
    public ObservableField<String> sexAndAge = new ObservableField<>();
    public ObservableField<String> signature = new ObservableField<>();
    public ObservableField<String> weight = new ObservableField<>();
    public ObservableField<String> height = new ObservableField<>();
    public ObservableField<String> bmi = new ObservableField<>();

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    MyInfoItemViewModel(Fragment fragment) {
        this.fragment = fragment;
        //默认显示缓存的数据
        UserBean oldUser = SPHelper.loadUser();
        if (oldUser != null) {
            setUserInfo(oldUser);
        }
        getUser();
    }

    private void setUserInfo(UserBean user) {
        // TODO: 2017/3/10 不一样的时候再改
        userProfileUrl.set(user.getProfile());
        userId.set(String.valueOf(user.getId()));
        userName.set(user.getUsername());
        if (!StringUtils.isEmpty(user.getSex())) {
            String str = (fragment.getString(R.string.sex_And_Age));
            // TODO: 2017/3/8 age
            sexAndAge.set(String.format(str, user.getSex(), 13));
        } else {
            // TODO: 2017/3/8 age
            sexAndAge.set("0岁");
        }
        signature.set(user.getSignature());
        weight.set(String.valueOf(user.getWeight()));
        height.set(String.valueOf(user.getHeight()));
        // TODO: 2017/3/15 bmi
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
        // TODO: 2017/3/15 userBean == null
        EditMyInfoFragment.launch(fragment.getActivity(), userBean);
    }

    private void onError(Throwable e) {

    }
}
