package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.IItemViewModel;
import com.qiyao.bysj.baselibrary.viewmodel.itemviewmodel.StaticItemViewModel;
import com.qiyao.bysj.runningisthebest.common.AppTimeUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunRecordDetailPagerFragment;

/**
 * Created by qiyao on 2017/3/13.
 */

public class RunRecordsItemViewModel extends BaseObservable implements IItemViewModel {
    private Fragment fragment;

    private String distance;
    private String duration;
    private String datetime;
    private String avgPace;
    private RunBean runBean;

    public RunRecordsItemViewModel(Fragment fragment, RunBean runBean) {
        this.fragment = fragment;
        this.runBean = runBean;
        setRunInfo(runBean);
    }

    private void setRunInfo(RunBean runBean) {
        distance = runBean.getDistance() != null ? String.valueOf(runBean.getDistance()) : "--";
        duration = runBean.getSpendTime() != null ? AppTimeUtils.getTime(runBean.getSpendTime()): "--";
        datetime = runBean.getBeginTime() != null ? TimeUtils.getFriendlyTimeSpanByNow(runBean.getBeginTime()) : "--";
        if (!duration.equals("--") && !distance.equals("--")) {
            avgPace = AppTimeUtils.getPace(runBean.getSpendTime(), runBean.getDistance());
        }
    }

    public void onClick(View view) {
        RunRecordDetailPagerFragment.launch(fragment.getActivity(), runBean);
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
    public String getAvgPace() {
        return avgPace;
    }

    @Bindable
    public String getDatetime() {
        return datetime;
    }
}
