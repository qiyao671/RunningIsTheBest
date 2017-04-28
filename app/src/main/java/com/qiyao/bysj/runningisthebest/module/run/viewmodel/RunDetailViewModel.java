package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.common.utils.AppUtils;
import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.util.Locale;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:20.
 * 类描述：
 */

public class RunDetailViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private RunBean runBean;

    private String distance;
    private String date;
    private String time;
    private String duration;
    private String avg_pace;
    private String avg_speed;
    private String calories = "-";

    public RunDetailViewModel(Fragment fragment, RunBean runBean) {
        this.fragment = fragment;
        this.runBean = runBean;
        this.distance =  String.format(Locale.CHINA, "%.2f", runBean.getDistance());
        this.duration = MyAppUtils.getTime(runBean.getSpendTime());
        avg_pace = MyAppUtils.getPace(runBean.getSpendTime(), runBean.getDistance());
        avg_speed = String.format(Locale.CHINA, "%.2f", runBean.getDistance() / runBean.getSpendTime() * ConstUtils.HOUR);
        if (runBean.getEnergy() != null) {
            calories = String.format(Locale.CHINA, "%.2f", runBean.getEnergy());
        }
        date = TimeUtils.millis2String(runBean.getStartRunTime(), "MM月dd日");
        time = TimeUtils.millis2String(runBean.getStartRunTime(), "HH:mm");
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
    public String getAvg_pace() {
        return avg_pace;
    }

    @Bindable
    public String getAvg_speed() {
        return avg_speed;
    }

    @Bindable
    public String getCalories() {
        return calories;
    }

    @Bindable
    public String getDate() {
        return date;
    }

    @Bindable
    public String getTime() {
        return time;
    }
}
