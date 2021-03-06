package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.android.databinding.library.baseAdapters.BR;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.ApproveBean;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.model.bean.UserBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.MomentLikeItemViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.RichMomentCommentItemViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.SuccinctMomentCommentItemViewModel;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import rx.Observable;

/**
 * Created by qiyao on 2017/3/17.
 */

public class LikesAndCommentsViewModel extends BaseObservable implements IViewModel {
    private Context context;
    private String commentType;

    private List<CommentBean> commentBeanList;
    private List<ApproveBean> approveBeanList;

    private OnItemBind<IItemViewModel> commentItemView;
    private ItemBinding<ApproveBean> likeItemView;
    public ObservableArrayList<IItemViewModel> comments = new ObservableArrayList<>();
    public ObservableArrayList<IItemViewModel> likes = new ObservableArrayList<>();

    public LikesAndCommentsViewModel(Context context, String commentType, List<CommentBean> commentBeanList, List<ApproveBean> approveBeanList) {
        this.context = context;
        this.commentType = commentType;
        this.commentBeanList = commentBeanList;
        this.approveBeanList = approveBeanList;
        initItemView();
        initItems();
    }

    private void initItems() {
        Observable.from(commentBeanList)
                .map(this::createCommentItemViewModel)
                .subscribe(comments::add);
        Observable.from(approveBeanList)
                .map(userBean -> new MomentLikeItemViewModel(context, userBean))
                .subscribe(likes::add);
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

        likeItemView = ItemBinding.of(BR.viewModel, R.layout.item_moment_like);
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

    @Bindable
    public OnItemBind<IItemViewModel> getCommentItemView() {
        return commentItemView;
    }

    @Bindable
    public ItemBinding<ApproveBean> getLikeItemView() {
        return likeItemView;
    }
}
