package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.widget.AdapterView;

import com.android.databinding.library.baseAdapters.BR;
import com.linearlistview.LinearListView;
import com.qiyao.bysj.baselibrary.component.OnItemClickListener;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.RichMomentCommentItemViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.SuccinctMomentCommentItemViewModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.OnItemBind;
import rx.Observable;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentCommentsViewModel extends BaseObservable implements IViewModel {
    private Context context;
    private String commentType;

    private List<CommentBean> commentBeanList;

    private OnItemBind<IItemViewModel> commentItemView;
    public ObservableArrayList<IItemViewModel> comments = new ObservableArrayList<>();
    private LinearListView.OnItemClickListener onItemClickListener;

    public MomentCommentsViewModel(Context context, String commentType, List<CommentBean> commentBeanList) {
        this.context = context;
        this.commentType = commentType;
        initItemView();
        initItems(commentBeanList);
    }

    public void initItems(List<CommentBean> commentBeanList) {
        this.commentBeanList = commentBeanList;
        comments.clear();
        Observable.from(commentBeanList)
                .map(this::createCommentItemViewModel)
                .subscribe(comments::add);
    }

    private void initItemView() {
        commentItemView = (itemBinding, position, item) -> {
            int layoutRes = 0;
            switch (item.getItemViewType()) {
                case SuccinctMomentCommentItemViewModel.TYPE_SUCCINCT:
                    layoutRes = R.layout.item_succint_moment_comment;
                    break;
                case RichMomentCommentItemViewModel.TYPE_RICH:
                    layoutRes = R.layout.item_rich_moment_comment;
                    break;
            }
            if (layoutRes != 0) {
                itemBinding.set(BR.viewModel, layoutRes);
            }
        };
    }

    private IItemViewModel createCommentItemViewModel(CommentBean item) {
        switch (commentType) {
            case SuccinctMomentCommentItemViewModel.TYPE_SUCCINCT:
                return new SuccinctMomentCommentItemViewModel(context, item);
            case RichMomentCommentItemViewModel.TYPE_RICH:
                return new RichMomentCommentItemViewModel(context, item);
            default:
                return null;
        }
    }

    void addComment(CommentBean commentBean) {
        comments.add(createCommentItemViewModel(commentBean));
    }

    @Bindable
    public OnItemBind<IItemViewModel> getCommentItemView() {
        return commentItemView;
    }

    public void setOnItemClickListener(LinearListView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Bindable
    public LinearListView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
}
