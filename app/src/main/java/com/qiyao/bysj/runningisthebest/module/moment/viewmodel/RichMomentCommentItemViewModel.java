package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class RichMomentCommentItemViewModel extends SuccinctMomentCommentItemViewModel {
    public static final String TYPE_RICH = "TYPE_RICH";
    public ObservableField<String> userProfile = new ObservableField<>();
    public ObservableField<String> datetime = new ObservableField<>();

    public RichMomentCommentItemViewModel(Context context, CommentBean commentBean) {
        super(context, commentBean);
        setMoreInfo(commentBean);
    }

    private void setMoreInfo(CommentBean commentBean) {
        // TODO: 2017/3/17 user profile datetime
    }

    @Override
    public String getItemViewType() {
        return TYPE_RICH;
    }

}
