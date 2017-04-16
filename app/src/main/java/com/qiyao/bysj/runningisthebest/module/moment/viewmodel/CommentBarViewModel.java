package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/20.
 */

public class CommentBarViewModel implements IViewModel, View.OnClickListener {
    private Context context;

    private OnCommentSuccessListener onCommentSuccessListener;
    private UserBean repliedUser;

    public ObservableField<String> content = new ObservableField<>("");
    public ObservableField<String> repliedUserName = new ObservableField<>();

    public CommentBarViewModel(Context context) {
        this.context = context;
    }

    public void setRepliedUser(UserBean repliedUser) {
        this.repliedUser = repliedUser;
        repliedUserName.set(repliedUser == null ? null : repliedUser.getUsername());
    }

    @Override
    public void onClick(View v) {
        CommentBean commentBean = new CommentBean();
        commentBean.setContent(content.get());
        if (repliedUser != null) {
            commentBean.setRepliedUserId(repliedUser.getId());
        }

    }

    public void setOnCommentSuccessListener(OnCommentSuccessListener onCommentSuccessListener) {
        this.onCommentSuccessListener = onCommentSuccessListener;
    }

    public interface OnCommentSuccessListener {
        void onCommentSuccess();
    }

}
