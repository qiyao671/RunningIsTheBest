package com.qiyao.bysj.runningisthebest.module.home.viewmodel.item;

import android.content.Context;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

/**
 * Created by qiyao on 2017/4/26.
 */

public class FriendRankingItemViewModel implements IItemViewModel {
    private Context context;

    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> profileUrl = new ObservableField<>();
    public ObservableDouble distance = new ObservableDouble();

    private RunBean runBean;

    public FriendRankingItemViewModel(Context context, RunBean runBean) {
        this.context = context;
        this.runBean = runBean;
        setData(runBean);
    }

    private void setData(RunBean runBean) {
        userName.set(runBean.getUser().getUsername());
        profileUrl.set(runBean.getUser().getProfile());
        distance.set(runBean.getTotalDistance());
    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }
}
