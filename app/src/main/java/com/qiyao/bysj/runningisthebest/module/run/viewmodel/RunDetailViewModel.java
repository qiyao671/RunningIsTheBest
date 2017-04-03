package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:20.
 * 类描述：
 */

public class RunDetailViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private RunBean runBean;

    private String distance;
    private String dateTime;
    private String duration;
    private String avg_pace;
    private String avg_speed;
    private String calories;


    @Bindable
    public String getDistance() {
        return distance;
    }

    @Bindable
    public String getDateTime() {
        return dateTime;
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
}
