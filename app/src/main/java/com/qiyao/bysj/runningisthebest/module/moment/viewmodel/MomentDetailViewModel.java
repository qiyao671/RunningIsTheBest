package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.linearlistview.LinearListView;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.model.bean.CommentBean;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.moment.ui.IMomentDetailView;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.RichMomentCommentItemViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item.SuccinctMomentCommentItemViewModel;
import com.trello.rxlifecycle.components.RxFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/20.
 */

public class MomentDetailViewModel extends BaseObservable implements IViewModel, LinearListView.OnItemClickListener, MomentCommentBarViewModel.OnCommentSuccessListener, View.OnClickListener {
    private Fragment fragment;
    private IMomentDetailView momentDetailView;

    private MomentBean momentBean;

    private MomentContentViewModel momentContentViewModel;
    private MomentCommentsViewModel momentCommentsViewModel;
    private MomentLikesViewModel momentLikesViewModel;
    private MomentCommentBarViewModel momentCommentBarViewModel;

    public MomentDetailViewModel(Fragment fragment, MomentBean momentBean, IMomentDetailView momentDetailView) {
        this.fragment = fragment;
        this.momentBean = momentBean;
        this.momentDetailView = momentDetailView;

        requestData();

        momentContentViewModel = new MomentContentViewModel(fragment.getActivity(), momentBean);
        momentContentViewModel.setOnCommentClickListener(this);
        // TODO: 2017/3/20
        momentCommentsViewModel = new MomentCommentsViewModel(fragment.getActivity(), RichMomentCommentItemViewModel.TYPE_RICH, momentBean.getCommentList());
        momentCommentsViewModel.setOnItemClickListener(this);
        momentLikesViewModel = new MomentLikesViewModel(fragment.getActivity(), momentBean.getApproveList());
        momentContentViewModel.setOnLikeMomentSuccessListener(momentLikesViewModel);

        momentCommentBarViewModel = new MomentCommentBarViewModel(fragment.getActivity(), momentBean.getId());
        momentCommentBarViewModel.setOnCommentSuccessListener(this);
    }

    @Bindable
    public MomentContentViewModel getMomentContentViewModel() {
        return momentContentViewModel;
    }

    @Bindable
    public MomentCommentBarViewModel getMomentCommentBarViewModel() {
        return momentCommentBarViewModel;
    }

    @Bindable
    public MomentCommentsViewModel getMomentCommentsViewModel() {
        return momentCommentsViewModel;
    }

    @Bindable
    public MomentLikesViewModel getMomentLikesViewModel() {
        return momentLikesViewModel;
    }

    @Override
    public void onItemClick(LinearListView parent, View view, int position, long id) {
        momentCommentBarViewModel.setRepliedUser(((SuccinctMomentCommentItemViewModel)parent.getAdapter().getItem(position)).getUser());
        momentDetailView.showKeyboard();
    }

    @Override
    public void onCommentSuccess(CommentBean commentBean) {
        momentCommentsViewModel.addComment(commentBean);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_comment:
                momentCommentBarViewModel.setRepliedUser(null);
                momentDetailView.showKeyboard();
                break;
        }
    }

    private void requestData() {
        HttpMethods.getInstance()
                .getMoment(momentBean.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(moment -> momentContentViewModel.setMoment(moment))
                .doOnNext(moment -> momentCommentsViewModel.initItems(moment.getCommentList()))
                .doOnNext(moment -> momentLikesViewModel.initItems(moment.getApproveList()))
                .compose(((RxFragment)fragment).bindToLifecycle())
                .doOnError(Throwable::printStackTrace)
                .subscribe(moment -> this.momentBean = moment, throwable -> ToastUtils.showShortToast(throwable.getMessage()));
    }
}
