package com.qiyao.bysj.runningisthebest.module.moment.viewmodel;

import android.content.Context;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.MomentBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentItemViewModel implements IItemViewModel {
    private Context context;

    private MomentBean momentBean;

    private MomentContentViewModel contentViewModel;
    private LikesAndCommentsViewModel likesAndCommentsViewModel;

    public MomentItemViewModel(Context context, MomentBean momentBean) {
        this.context = context;
        this.momentBean = momentBean;
        contentViewModel = new MomentContentViewModel(context, momentBean);
        // TODO: 2017/3/17
//        likesAndCommentsViewModel = new LikesAndCommentsViewModel(context)
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    @Bindable
    public MomentContentViewModel getContentViewModel() {
        return contentViewModel;
    }

    @Bindable
    public LikesAndCommentsViewModel getLikesAndCommentsViewModel() {
        return likesAndCommentsViewModel;
    }
}
