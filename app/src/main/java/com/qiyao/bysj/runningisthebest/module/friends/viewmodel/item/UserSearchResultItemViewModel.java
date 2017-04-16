package com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/13.
 */

public class UserSearchResultItemViewModel extends FriendItemViewModel implements IItemViewModel {
    public ObservableInt status = new ObservableInt();

    public UserSearchResultItemViewModel(Context context, UserBean user) {
        super(context, user);
        status.set(user.getRelationStatus());
    }

    @Override
    public String getItemViewType() {
        return null;
    }

    public void addFriend(View v) {
        HttpMethods.getInstance()
                .addFriend(getUser().getId())
                .subscribeOn(Schedulers.newThread())
                .compose(((RxAppCompatActivity) getContext()).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(ToastUtils::showLongToast, e -> ToastUtils.showLongToast(e.getMessage()));
    }
}
