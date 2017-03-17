package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class SuccinctMomentCommentItemViewModel extends BaseObservable implements IItemViewModel {
    public static final String TYPE_SUCCINCT = "TYPE_SUCCINCT";
    private Context context;

    private CommentBean commentBean;

    private String userName;
    private String repliedUserName;
    private String content;

    public SuccinctMomentCommentItemViewModel(Context context, CommentBean commentBean) {
        this.context = context;
        this.commentBean = commentBean;
        setCommentInfo(commentBean);
    }

    private void setCommentInfo(CommentBean comment) {
        userName = String.valueOf(comment.getUserId());
        repliedUserName = String.valueOf(comment.getRepliedUserId());
        content = comment.getContent();
    }

    @Override
    public String getItemViewType() {
        return TYPE_SUCCINCT;
    }
    @Bindable
    public String getUserName() {
        return userName;
    }

    @Bindable
    public String getRepliedUserName() {
        return repliedUserName;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public Context getContext() {
        return context;
    }
}
