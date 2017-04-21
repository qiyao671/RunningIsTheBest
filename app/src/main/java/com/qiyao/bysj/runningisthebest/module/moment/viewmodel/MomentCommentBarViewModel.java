package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.KeyboardUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.common.SPHelper;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.Calendar;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentCommentBarViewModel implements IViewModel, View.OnClickListener {
    private Context context;

    private OnCommentSuccessListener onCommentSuccessListener;
    private UserBean repliedUser;
    private int momentId;

    public ObservableField<String> content = new ObservableField<>("");
    public ObservableField<String> repliedUserName = new ObservableField<>();

    MomentCommentBarViewModel(Context context, int momentId) {
        this.context = context;
        this.momentId = momentId;
    }

    void setRepliedUser(UserBean repliedUser) {
        this.repliedUser = repliedUser;
        repliedUserName.set(repliedUser == null ? null : repliedUser.getUsername());
    }

    @Override
    public void onClick(View v) {
        CommentBean commentBean = new CommentBean();
        commentBean.setMomentId(momentId);
        commentBean.setContent(content.get());
        if (repliedUser != null) {
            commentBean.setRepliedUserId(repliedUser.getId());
            commentBean.setRepliedUser(repliedUser);
        }
        commentBean.setUser(SPHelper.loadUser());
        commentBean.setGmtCreate(Calendar.getInstance().getTimeInMillis());
        submit(commentBean);
    }

    void setOnCommentSuccessListener(OnCommentSuccessListener onCommentSuccessListener) {
        this.onCommentSuccessListener = onCommentSuccessListener;
    }

    interface OnCommentSuccessListener {
        void onCommentSuccess(CommentBean commentBean);
    }

    private void submit(CommentBean comment) {
        HttpMethods.getInstance().commentMoment(comment)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> onCommentSuccessListener.onCommentSuccess(comment))
                .doOnSubscribe(() -> content.set(""))
                .doOnSubscribe(() -> KeyboardUtils.hideSoftInput((AppCompatActivity) context))
                .doOnError(Throwable::printStackTrace)
                .subscribe(ToastUtils::showShortToast, e -> ToastUtils.showShortToast(e.getMessage()));
    }
}
