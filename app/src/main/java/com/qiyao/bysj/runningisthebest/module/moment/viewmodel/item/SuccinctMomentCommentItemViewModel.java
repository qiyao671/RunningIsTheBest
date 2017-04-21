package com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.databinding.ItemFriendBinding;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class SuccinctMomentCommentItemViewModel implements IItemViewModel {
    public static final String TYPE_SUCCINCT = "TYPE_SUCCINCT";
    private Context context;

    private CommentBean commentBean;

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> repliedUserName = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableBoolean isReply = new ObservableBoolean();

    public SuccinctMomentCommentItemViewModel(Context context, CommentBean commentBean) {
        this.context = context;
        this.commentBean = commentBean;
        setCommentInfo(commentBean);
    }

    private void setCommentInfo(CommentBean comment) {
        userName.set(comment.getUser().getUsername());
        if (comment.getRepliedUser() != null) {
            repliedUserName.set(comment.getRepliedUser().getUsername());
        }
        isReply.set(comment.getRepliedUser() != null);
        content.set(comment.getContent());
    }

    @Override
    public String getItemViewType() {
        return TYPE_SUCCINCT;
    }

    public UserBean getUser() {
        return commentBean.getUser();
    }
}
