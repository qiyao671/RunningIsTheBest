package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.common.utils.ToastUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.Constants;
import com.qiyao.bysj.runningisthebest.common.MyAppUtils;
import com.qiyao.bysj.runningisthebest.model.bean.TotalRunBean;
import com.qiyao.bysj.runningisthebest.model.net.HttpMethods;

import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.qiyao.bysj.runningisthebest.common.Constants.FLAG_TOTAL_RUN_MONTH;
import static com.qiyao.bysj.runningisthebest.common.Constants.TYPE_MONTH;
import static com.qiyao.bysj.runningisthebest.common.Constants.TYPE_TOTAL;
import static com.qiyao.bysj.runningisthebest.common.Constants.TYPE_WEEK;

/**
 * Created by qiyao on 2017/3/9.
 */

public class TotalRunViewModel implements IViewModel {
    private Fragment fragment;

//    private ObservableField<String> distance = new ObservableField<>("--");
    public ObservableField<String> distance = new ObservableField<>("--");
    public ObservableField<String> duration = new ObservableField<>("--");
    public ObservableField<String> calories = new ObservableField<>("--");
    public ObservableField<String> avgPace = new ObservableField<>("--");
    public ObservableField<String> avgSpeed = new ObservableField<>("--");
    public ObservableField<String> title = new ObservableField<>();

    public TotalRunViewModel(Fragment fragment, String type) {
        this.fragment = fragment;
        setTitle(type);
        getTotalRun(type);
    }

    private void getTotalRun(String type) {
        int flag;
        switch (type) {
            case Constants.TYPE_WEEK:
                flag = Constants.FLAG_TOTAL_RUN_WEEK;
                break;
            case Constants.TYPE_MONTH:
                flag = Constants.FLAG_TOTAL_RUN_MONTH;
                break;
            case Constants.TYPE_TOTAL:
                flag = Constants.FLAG_TOTAL_RUN;
                break;
            default:
                return;
        }
        HttpMethods.getInstance().getTotalLogInfo(flag)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setRunInfo, e -> ToastUtils.showShortToast(e.getMessage()));
    }

    private void setRunInfo(TotalRunBean total) {
        distance.set(String.valueOf(total.getTotalDistance()));
        calories.set(String.valueOf(total.getTotalEnergy()));
        duration.set(MyAppUtils.getTime(total.getTotalSpendTime()));
        if (total.getTotalDistance() > 0 && total.getTotalSpendTime() > 0) {
            avgPace.set(MyAppUtils.getPace(total.getTotalSpendTime(), total.getTotalDistance()));
            avgSpeed.set(String.format(Locale.CHINA, "%.2f", total.getTotalDistance() / total.getTotalSpendTime() * ConstUtils.HOUR));
        }
    }

    private void setTitle(String type) {
        switch (type) {
            case TYPE_WEEK:
                title.set(fragment.getString(R.string.this_week));
                break;
            case TYPE_MONTH:
                title.set(TimeUtils.millis2String(TimeUtils.getNowTimeMills(), "yyyy年MM月"));
                break;
            case TYPE_TOTAL:
                title.set(fragment.getString(R.string.my_run_life));
                break;
        }
    }
}
