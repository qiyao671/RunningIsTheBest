package com.qiyao.bysj.runningisthebest.module.run.viewmodel;

import android.app.Fragment;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.run.ui.IRunTrackView;
import com.trello.rxlifecycle.components.RxFragment;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lvqiyao (amorfatilay@163.com).
 * 2017/3/11 22:15.
 * 类描述：
 */

public class RunTrackViewModel extends BaseObservable implements IViewModel {
    private Fragment fragment;
    private IRunTrackView runTrackView;

    private String distance;
    private String duration;
    private String avgPace;
    private String calories;
    private String datetime;

    public RunTrackViewModel(Fragment fragment, RunBean runBean, IRunTrackView runTrackView) {
        this.fragment = fragment;
        setRunInfo(runBean);
        this.runTrackView = runTrackView;
        setTrack(runBean.getId());
    }

    private void setRunInfo(RunBean runBean) {
        distance = runBean.getDistance() != null ? String.valueOf(runBean.getDistance()) : "--";
        duration = runBean.getSpendTime() != null ? MyAppUtils.getTime(runBean.getSpendTime()) : "--";
        datetime = runBean.getStartRunTime() != null ? TimeUtils.getFriendlyTimeSpanByNow(runBean.getStartRunTime()) : "--";
        if (!duration.equals("--") && !distance.equals("--")) {
            avgPace = MyAppUtils.getPace(runBean.getSpendTime(), runBean.getDistance());
        }
        calories = runBean.getEnergy() != null ? String.valueOf(runBean.getEnergy()) : "--";
    }

    private void setTrack(Integer logId) {
        HttpMethods.getInstance()
                .getRunTracks(logId)
                .subscribeOn(Schedulers.newThread())
                .compose(((RxFragment) fragment).bindToLifecycle())
                .flatMap(Observable::from)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(Throwable::printStackTrace)
                .subscribe(runTrackView::addTrackToMap, throwable -> ToastUtils.showShortToast(throwable.getMessage()));
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
