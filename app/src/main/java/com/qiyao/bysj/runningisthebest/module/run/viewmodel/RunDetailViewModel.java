package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:20.
 * 类描述：
 */

public class RunDetailViewModel implements IViewModel {
    private Fragment fragment;

    private RunBean runBean;

    private String distance;
    private String dateTime;
    private String duration;
    private String avg_pace;
    private String avg_speed;
    private String calories;


    public String getDistance() {
        return distance;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDuration() {
        return duration;
    }

    public String getAvg_pace() {
        return avg_pace;
    }

    public String getAvg_speed() {
        return avg_speed;
    }

    public String getCalories() {
        return calories;
    }
}
