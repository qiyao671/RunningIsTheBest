package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.BestRunBean;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;
import com.qiyao.bysj.runningisthebest.module.home.ui.BestRunFragment;
import com.qiyao.bysj.runningisthebest.module.run.ui.RunRecordDetailPagerFragment;
import com.trello.rxlifecycle.components.RxFragment;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiyao on 2017/3/9.
 */

public class BestRunViewModel implements IViewModel {
    private Fragment fragment;

    public ObservableField<String> farthestRun = new ObservableField<>("0.00");
    public ObservableField<String> longestRun = new ObservableField<>("00:00:00");
    public ObservableField<String> fastestAvgSpeed = new ObservableField<>("0.00");
    public ObservableField<String> fastestPace = new ObservableField<>("0'0\"");
    public ObservableField<String> fiveKmPb = new ObservableField<>("--:--:--");
    public ObservableField<String> tenKmPb = new ObservableField<>("--:--:--");
    public ObservableField<String> halfMarathonPb = new ObservableField<>("--:--:--");
    public ObservableField<String> fullMarathonPb = new ObservableField<>("--:--:--");

    private BestRunBean bestRunBean;

    int userId = -1;

    public BestRunViewModel(Fragment fragment) {
        this.fragment = fragment;
        getBestRun();
    }

    public BestRunViewModel(Fragment fragment, int userId) {
        this.userId = userId;
        this.fragment = fragment;
        getBestRun();
    }

    private void getBestRun() {
        Observable<BestRunBean> bestRunObservable;
        if (userId > 0) {
            bestRunObservable = HttpMethods.getInstance().getBestRun(userId);
        } else {
            bestRunObservable = HttpMethods.getInstance().getBestRun();
        }
        bestRunObservable
                .subscribeOn(Schedulers.newThread())
                .filter(bestRun -> bestRun != null)
                .compose(((RxFragment) fragment).bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setBestRunInfo, this::onError);
    }

    private void setBestRunInfo(BestRunBean bestRun) {
        this.bestRunBean = bestRun;

        if (bestRun.getFarthestLogInfo() != null && bestRun.getFarthestLogInfo().getDistance() != null) {
            this.farthestRun.set(String.valueOf(bestRun.getFarthestLogInfo().getDistance()));
        }
        if (bestRun.getLongestRunLogVO() != null && bestRun.getLongestRunLogVO().getSpendTime() != null) {
            this.longestRun.set(MyAppUtils.getTime(bestRun.getLongestRunLogVO().getSpendTime()));
        }
        if (bestRun.getFullMaPB() != null && bestRun.getFullMaPB().getSpendTime() != null) {
            this.fullMarathonPb.set(MyAppUtils.getTime(bestRun.getFullMaPB().getSpendTime()));
        }
        if (bestRun.getHalfMaPB() != null && bestRun.getHalfMaPB().getSpendTime() != null) {
            this.halfMarathonPb.set(MyAppUtils.getTime(bestRun.getHalfMaPB().getSpendTime()));
        }
        if (bestRun.getFivePB() != null && bestRun.getFivePB().getSpendTime() != null) {
            this.fiveKmPb.set(MyAppUtils.getTime(bestRun.getFivePB().getSpendTime()));
        }
        if (bestRun.getTenPB() != null && bestRun.getTenPB().getSpendTime() != null) {
            this.tenKmPb.set(MyAppUtils.getTime(bestRun.getTenPB().getSpendTime()));
        }
        if (bestRun.getFastSpeed() != null
                && bestRun.getFastSpeed().getSpendTime() != null
                && bestRun.getFastSpeed().getDistance() != null) {
            double speed = bestRun.getFastSpeed().getDistance()
                    / bestRun.getFastSpeed().getSpendTime()
                    * ConstUtils.HOUR;
            this.fastestAvgSpeed.set(String.valueOf(speed));
        }
    }

    private void onError(Throwable throwable) {
        Log.e("getBestRun", "onError: " + throwable.getMessage(), throwable);
    }

    public void onClick(View view) {
        if (bestRunBean == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.farthest_run:
                if (bestRunBean.getFarthestLogInfo() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getFarthestLogInfo());
                }
                break;
            case R.id.longest_run:
                if (bestRunBean.getLongestRunLogVO() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getLongestRunLogVO());
                }
                break;
            case R.id.fastest_avg_speed:
                if (bestRunBean.getFullMaPB() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getFullMaPB());
                }
                break;
            case R.id.fastest_pace:
/*                if (bestRunBean.getFarthestLogInfo() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getFarthestLogInfo());
                }*/
                break;
            case R.id.five_km_pb:
                if (bestRunBean.getFivePB() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getFivePB());
                }
                break;
            case R.id.ten_km_pb:
                if (bestRunBean.getTenPB() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getTenPB());
                }
                break;
            case R.id.half_marathon_pb:
                if (bestRunBean.getHalfMaPB() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getHalfMaPB());
                }
                break;
            case R.id.full_marathon_pb:
                if (bestRunBean.getFullMaPB() != null) {
                    launchRunRecordDetailPagerFragment(bestRunBean.getFullMaPB());
                }
                break;
        }
    }

    private void launchRunRecordDetailPagerFragment(RunBean runBean) {
        RunRecordDetailPagerFragment.launch(fragment.getActivity(), runBean);
    }
}
