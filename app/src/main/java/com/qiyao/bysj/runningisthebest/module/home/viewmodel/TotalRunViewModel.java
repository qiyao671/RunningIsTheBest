package com.qiyao.bysj.runningisthebest.module.home.viewmodel;

import android.app.Fragment;
import android.databinding.ObservableField;

import com.qiyao.bysj.baselibrary.common.utils.ConstUtils;
import com.qiyao.bysj.baselibrary.common.utils.TimeUtils;
import com.qiyao.bysj.baselibrary.viewmodel.IViewModel;
import com.qiyao.bysj.runningisthebest.R;
import com.qiyao.bysj.runningisthebest.common.AppTimeUtils;
import com.qiyao.bysj.runningisthebest.model.bean.RunBean;

import java.util.Locale;

/**
 * Created by qiyao on 2017/3/9.
 */

public class TotalRunViewModel implements IViewModel {
    private Fragment fragment;

    public static final String TYPE_WEEK = "WEEK";
    public static final String TYPE_MONTH = "MONTH";
    public static final String TYPE_TOTAL = "TOTAL";
//    private ObservableField<String> distance = new ObservableField<>("--");
    private ObservableField<String> duration = new ObservableField<>("--");
    private ObservableField<String> calories = new ObservableField<>("--");
    private ObservableField<String> pace = new ObservableField<>("--");
    private ObservableField<String> avg_pace = new ObservableField<>("--");
    private ObservableField<String> title = new ObservableField<>();

    public TotalRunViewModel(Fragment fragment, String type) {
        this.fragment = fragment;
        getTotalRun(type);
    }

    private void getTotalRun(String type) {

    }

    private void setRunInfo(RunBean run, String type) {
/*        if (run.getTotalDistance() != null) {
            ditance.set(String.valueOf(run.getTotalDistance()));
        }*/
        if (run.getTotalEnergy() != null) {
            calories.set(String.valueOf(run.getTotalEnergy()));
        }
        if (run.getTotalSpendTime() != null) {
            duration.set(AppTimeUtils.getTime(run.getTotalSpendTime()));
        }
        if (run.getTotalDistance() != null && run.getTotalDistance() > 0
                && run.getTotalSpendTime() != null && run.getTotalSpendTime() > 0) {
            pace.set(AppTimeUtils.getPace(run.getTotalSpendTime(), run.getTotalDistance()));
            long hours = run.getTotalSpendTime() / ConstUtils.HOUR;
            avg_pace.set(String.valueOf(run.getTotalDistance() / hours));
        }
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
