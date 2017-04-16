package com.qiyao.bysj.runningisthebest.module.friends.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableInt;

import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.model.event.MessageEvent;
import com.qiyao.bysj.baselibrary.model.event.RxBus;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        HttpMethods.getInstance().agreeAddFriend(getUser().getId())
                .subscribeOn(Schedulers.newThread())
                .compose(((RxAppCompatActivity) getContext()).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAcceptSuccess, error -> ToastUtils.showShortToast(error.getMessage()));
    }

    private void onAcceptSuccess(String msg) {
        ToastUtils.showShortToast(msg);
        status.set(Constants.FRIEND_STATUS_HAS_BEEN_FRIENDS);
        RxBus.getDefault().post(new MessageEvent(Constants.EVENT_REFRESH_FRIENDS_LIST));
    }
}
