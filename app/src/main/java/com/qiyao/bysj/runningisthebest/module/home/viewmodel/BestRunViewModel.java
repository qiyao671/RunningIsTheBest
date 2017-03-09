package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.util.Log;

import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.trello.rxlifecycle.components.RxFragment;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/9.
 */

public class BestRunViewModel implements IViewModel {
    private Fragment fragment;

    public ObservableField<String> farthestRun = new ObservableField<>("0.00");
    public ObservableField<String> longestRun = new ObservableField<>("00:00:00");
    public ObservableField<String> fastestAvgPace = new ObservableField<>("0.00");
    public ObservableField<String> fastestPace = new ObservableField<>("0'0\"");
    public ObservableField<String> fiveKmPb = new ObservableField<>("--:--:--");
    public ObservableField<String> tenKmPb = new ObservableField<>("--:--:--");
    public ObservableField<String> halfMarathonPb = new ObservableField<>("--:--:--");
    public ObservableField<String> fullMarathonPb = new ObservableField<>("--:--:--");

    private BestRunBean bestRunBean;

    public BestRunViewModel(Fragment fragment) {
        this.fragment = fragment;
        getBestRun();
    }

    private void getBestRun() {
        HttpMethods httpMethods = HttpMethods.getInstance();
        httpMethods.getBestRun()
                .subscribeOn(Schedulers.newThread())
                .filter(bestRun -> bestRun != null)
                .compose(((RxFragment) fragment).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setBestRunInfo, this::onError);
    }

    private void setBestRunInfo(BestRunBean bestRun) {
        this.bestRunBean = bestRun;

        this.farthestRun.set(String.valueOf(bestRun.getFarthestLogInfo().getDistance()));
        this.longestRun.set(String.valueOf(bestRun.getLongestRunLogVO().getSpendTime()));
        this.fullMarathonPb.set(String.valueOf(bestRun.getFullMaPB().getSpendTime()));
        this.halfMarathonPb.set(String.valueOf(bestRun.getHalfMaPB().getSpendTime()));
        this.fiveKmPb.set(String.valueOf(bestRun.getFivePB().getSpendTime()));
        this.tenKmPb.set(String.valueOf(bestRun.getTenPB().getSpendTime()));
    }

    private void onError(Throwable throwable) {
        Log.e("getBestRun", "onError: " + throwable.getMessage(), throwable);
    }
}
