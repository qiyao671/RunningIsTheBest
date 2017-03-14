package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.content.pm.ProviderInfo;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.common.AppTimeUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.util.Locale;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:15.
 * 类描述：
 */

public class RunTrackViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private String distance;
    private String duration;
    private String avgPace;
    private String calories;
    private String datetime;

    public RunTrackViewModel(Fragment fragment, RunBean runBean) {
        this.fragment = fragment;
        setRunInfo(runBean);
    }

    private void setRunInfo(RunBean runBean) {
        distance = runBean.getDistance() != null ? String.valueOf(runBean.getDistance()) : "--";
        duration = runBean.getSpendTime() != null ? String.format(Locale.US, "Local time: %tT", runBean.getSpendTime()) : "--";
        datetime = runBean.getBeginTime() != null ? TimeUtils.getFriendlyTimeSpanByNow(runBean.getBeginTime()) : "--";
        if (!duration.equals("--") && !distance.equals("--")) {
            avgPace = AppTimeUtils.getPace(runBean.getSpendTime(), runBean.getDistance());
        }
        calories = runBean.getEnergy() != null ? String.valueOf(runBean.getEnergy()) : "--";
    }

    @Bindable
    public Fragment getFragment() {
        return fragment;
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
    public String getCalories() {
        return calories;
    }

    @Bindable
    public String getDatetime() {
        return datetime;
    }
}
