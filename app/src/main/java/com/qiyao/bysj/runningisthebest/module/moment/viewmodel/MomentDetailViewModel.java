package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;

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
    public ObservableBoolean isCommentBarVisible = new ObservableBoolean(false);

    public MomentDetailViewModel(Fragment fragment, MomentBean momentBean) {
        this.fragment = fragment;
        this.momentBean = momentBean;
        momentContentViewModel = new MomentContentViewModel(fragment.getActivity(), momentBean);
        // TODO: 2017/3/20
//        momentCommentsViewModel = new MomentCommentsViewModel()
//        momentLikesViewModel = new MomentLikesViewModel()
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

    public MomentCommentsViewModel getMomentCommentsViewModel() {
        return momentCommentsViewModel;
    }

    public MomentLikesViewModel getMomentLikesViewModel() {
        return momentLikesViewModel;
    }
}
