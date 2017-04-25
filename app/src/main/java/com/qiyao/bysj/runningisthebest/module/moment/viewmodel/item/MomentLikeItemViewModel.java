package com.qiyao.bysj.runningisthebest.module.moment.viewmodel.item;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.ApproveBean;

/**
 * Created by qiyao on 2017/3/17.
 */

public class MomentLikeItemViewModel extends BaseObservable implements IItemViewModel {
    private Context context;

    private ApproveBean approveBean;

    public ObservableField<String> userProfile = new ObservableField<>();

    public MomentLikeItemViewModel(Context context, ApproveBean approveBean) {
        this.context = context;
        this.approveBean = approveBean;
        userProfile.set(approveBean.getUser().getProfile());
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    public int getUserId() {
        return approveBean.getUserId();
    }
}
