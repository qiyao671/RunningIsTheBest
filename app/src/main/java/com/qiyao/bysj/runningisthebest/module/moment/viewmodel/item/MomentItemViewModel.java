package com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;
import com.qiyao.bysj.runningisthebest.module.moment.ui.MomentDetailFragment;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentCommentsViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentContentViewModel;
import com.qiyao.bysj.runningisthebest.module.moment.viewmodel.MomentLikesViewModel;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentItemViewModel extends BaseObservable implements IItemViewModel, View.OnClickListener {
    private Context context;

    private MomentBean momentBean;

    public ObservableField<MomentContentViewModel> contentViewModel = new ObservableField<>();
    public ObservableField<MomentLikesViewModel> likesViewModel = new ObservableField<>();
    public ObservableField<MomentCommentsViewModel> commentsViewModel = new ObservableField<>();
    public ObservableBoolean isCommentBarVisible = new ObservableBoolean(false);

    public MomentItemViewModel(Context context, MomentBean momentBean) {
        this.context = context;
        this.momentBean = momentBean;
        contentViewModel.set(new MomentContentViewModel(context, momentBean));
        // TODO: 2017/3/17
        likesViewModel.set(new MomentLikesViewModel(context, momentBean.getApproveList()));
        commentsViewModel.set(new MomentCommentsViewModel(context, SuccinctMomentCommentItemViewModel.TYPE_SUCCINCT,momentBean.getCommentList()));
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }


    @Override
    public void onClick(View view) {
        MomentDetailFragment.launch(context, momentBean);
    }
}
