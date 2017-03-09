package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;

/**
 * Created by qiyao on 2017/3/9.
 */

public class BestRunViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;

    private String farthestRun = "0.00";
    private String longestRun = "00:00:00";
    private String fastestAvgPace = "0.00";
    private String fastestPace = "0'0\"";
    private String fiveKmPb = "--:--:--";
    private String tenKmPb = "--:--:--";
    private String halfMarathonPb = "--:--:--";
    private String fullMarathonPb = "--:--:--";


    @Bindable
    public String getFarthestRun() {
        return farthestRun;
    }

    @Bindable
    public String getLongestRun() {
        return longestRun;
    }

    @Bindable
    public String getFastestAvgPace() {
        return fastestAvgPace;
    }

    @Bindable
    public String getFastestPace() {
        return fastestPace;
    }

    @Bindable
    public String getFiveKmPb() {
        return fiveKmPb;
    }

    @Bindable
    public String getTenKmPb() {
        return tenKmPb;
    }

    @Bindable
    public String getHalfMarathonPb() {
        return halfMarathonPb;
    }

    @Bindable
    public String getFullMarathonPb() {
        return fullMarathonPb;
    }
}
