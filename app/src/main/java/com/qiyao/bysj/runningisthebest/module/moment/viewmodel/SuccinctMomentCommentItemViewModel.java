package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;

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

    public SuccinctMomentCommentItemViewModel(Context context, CommentBean commentBean) {
        this.context = context;
        this.commentBean = commentBean;
        setCommentInfo(commentBean);
    }

    private void setCommentInfo(CommentBean comment) {
        userName.set(String.valueOf(comment.getUserId()));
        repliedUserName.set(String.valueOf(comment.getRepliedUserId()));
        content.set(comment.getContent());
    }

    @Override
    public String getItemViewType() {
        return TYPE_SUCCINCT;
    }
}
