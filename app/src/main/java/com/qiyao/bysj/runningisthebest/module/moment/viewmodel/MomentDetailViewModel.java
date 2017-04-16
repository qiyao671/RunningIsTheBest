package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.RichMomentCommentItemViewModel;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentDetailViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private MomentBean momentBean;

    private MomentContentViewModel momentContentViewModel;
    private MomentCommentsViewModel momentCommentsViewModel;
    private MomentLikesViewModel momentLikesViewModel;
    private CommentBarViewModel commentBarViewModel;

    public MomentDetailViewModel(Fragment fragment, MomentBean momentBean) {
        this.fragment = fragment;
        this.momentBean = momentBean;
        momentContentViewModel = new MomentContentViewModel(fragment.getActivity(), momentBean);
        // TODO: 2017/3/20
        momentCommentsViewModel = new MomentCommentsViewModel(fragment.getActivity(), RichMomentCommentItemViewModel.TYPE_RICH, momentBean.getCommentList());
        momentLikesViewModel = new MomentLikesViewModel(fragment.getActivity(), momentBean.getApproveList());
        commentBarViewModel = new CommentBarViewModel(fragment.getActivity());
    }

    @Bindable
    public MomentContentViewModel getMomentContentViewModel() {
        return momentContentViewModel;
    }

    @Bindable
    public CommentBarViewModel getCommentBarViewModel() {
        return commentBarViewModel;
    }

    @Bindable
    public MomentCommentsViewModel getMomentCommentsViewModel() {
        return momentCommentsViewModel;
    }

    @Bindable
    public MomentLikesViewModel getMomentLikesViewModel() {
        return momentLikesViewModel;
    }
}
