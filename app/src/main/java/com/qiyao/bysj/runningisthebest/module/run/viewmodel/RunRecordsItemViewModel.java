package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.StringUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsItemViewModel extends BaseObservable implements IItemViewModel {
    private String distance;
    private String duration;
    private String datetime;
    private String pace;

    private RunBean runBean;
    public RunRecordsItemViewModel(RunBean runBean) {
        this.runBean = runBean;
        distance = String.valueOf(runBean.getDistance());
        duration = String.valueOf(runBean.getSpendTime());
        datetime = String.valueOf(runBean.getBeginTime());
//        pace = String.valueOf(runBean.getSpendTime() / )
        pace = "";
    }

    public void onClick(View view) {

    }

    @Override
    public String getItemViewType() {
        return StaticItemViewModel.TYPE_ITEM;
    }

    @Bindable
    public String getDistance() {
        return distance;
    }

    @Bindable
    public String getDuration() {
        return duration;
    }

    @Bindable
    public String getPace() {
        return pace;
    }

    @Bindable
    public String getDatetime() {
        return datetime;
    }
}
